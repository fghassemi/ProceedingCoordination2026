data class GossipValue<ID : Comparable<ID>, Value>(%\label{code:gossipvalue-start}%
  val best: Value, %\label{code:gossipvalue-best}%
  val path: List<ID> = emptyList(), %\label{code:gossipvalue-path}%
) {
  fun addHop(id: ID) = GossipValue(best, path + id) %\label{code:gossipvalue-addHop}%
}%\label{code:gossipvalue-end}%

fun <ID : Comparable<ID>, Value> Aggregate<ID>.findMaxOf(%\label{code:findMaxOf-start}%
  local: Value,
  comparator: Comparator<in Value>,
): Value { %\label{code:findMaxOf-signature-end}%
  val localGossip = GossipValue<ID, Value>(best = local) %\label{code:findMaxOf-localGossip}%
  return share(localGossip) { gossip -> %\label{code:findMaxOf-share}%
    gossip.neighbors.values.fold(localGossip) { current, neighbor -> %\label{code:findMaxOf-fold}%
      when { %\label{code:findMaxOf-when}%
        localId in neighbor.path -> current // Ignore paths that loop back %\label{code:findMaxOf-loopCheck}%
        else -> when(comparator.compare(neighbor.best, current.best)) {
          0 -> when { // If values tie, select based on path %\label{code:findMaxOf-tieCheck}%
            neighbor.path.size == current.path.size -> %\label{code:findMaxOf-tieEqualLen}%
              // If paths are of equal length, compare their last element
              // (they necessarily come from different neighbors)
              listOf(neighbor, current).minBy { it.path.last() } %\label{code:findMaxOf-tieBreakFirst}%
            // Select the shortest path
            else -> listOf(neighbor, current).minBy { it.path.size } %\label {code:findMaxOf-tieBreakShortest}%
          }
          // Pick the best value according to the comparator
          in 1..Int.MAX_VALUE -> neighbor %\label{code:findMaxOf-pickBest}%
          else -> current
        }
      }
    }.addHop(localId) %\label{code:findMaxOf-addHop}%
  }.best %\label{code:findMaxOf-returnBest}%
}%\label{code:findMaxOf-end}%
