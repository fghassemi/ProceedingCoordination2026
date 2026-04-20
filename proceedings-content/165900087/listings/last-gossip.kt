data class GossipValue<ID : Comparable<ID>, Value>(
    val best: Value,
    val path: List<ID> = emptyList(),
) {
    fun base(local: Value, id: ID) = GossipValue(local, listOf(id))
    fun addHop(id: ID) = GossipValue(best, path + id)
}

fun <ID : Comparable<ID>, Value> loopLessFind(
    local: Value,
    selector: (Value, Value) -> Value = { first, _ -> first },
): Value {
    val localGossip = GossipValue<ID, Value>(best = local)
    return share(localGossip) { gossip ->
        val neighbors = gossip.neighbors.ids.set
        val result = gossip.fold(localGossip) { current, (id, next) ->
            val actualNext = if (isValidPath(next.path))
                next
            else
                GossipValue(local, listOf(id))
            val candidateValue = selector(current.best, actualNext.best)
            when {
                current.best == actualNext.best -> {
                    listOf(current, actualNext).minBy { path.size }
                }
                candidateValue == current.best -> current
                else -> actualNext
            }
        }.addHop(localId)
        result
    }.best
}