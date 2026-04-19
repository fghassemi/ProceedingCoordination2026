data class GossipValue<ID : Comparable<ID>, Type>(val best: Type, val local: Type, val path: List<ID> = emptyList()) {
    fun base(id: ID) = GossipValue(local, local, listOf(id))
}

fun <ID : Comparable<ID>, Type> Aggregate<ID>.gossip(local: Type, comparator: Comparator<Type>): Type {
    val localGossip = GossipValue<ID, Type>(best = local, local = local)
    return share(localGossip) { gossip ->
        val neighbors = gossip.neighbors.toSet()
        val result = gossip.foldWithId(localGossip) { current, id, next ->
            val valid = next.path.asReversed().asSequence().drop(1).none { it == localId || it in neighbors }
            val actualNext = if (valid) next else next.base(id)
            val candidate = comparator.compare(current.best, actualNext.best)
            when {
                candidate > 0 -> current
                candidate == 0 -> listOf(current, next).minBy { it.path.size }
                else -> actualNext
            }
        }
        GossipValue(result.best, local, result.path + localId)
    }.best
}
