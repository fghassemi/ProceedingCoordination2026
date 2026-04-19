data class GossipValue<ID, Type>(val best: Type, val local: Type, val path: List<ID> = emptyList()) {
    fun base(id: ID) = GossipValue(local, local, listOf(id))
}

fun <ID, Type> Aggregate<ID>.secondGossip(
    initial: Type,
    selector: Comparator<Type>,
): Type {
    val local = GossipValue<ID, Type>(initial, initial)
    return share(local) { gossip ->
        val result = gossip.foldWithId(local) { current, id, next ->
            val actualNext = if (localId in next.path) next.base(id) else next
            val candidate = selector.compare(current.best, actualNext.best)
            when {
                candidate > 0 -> current
                candidate == 0 -> listOf(current, next).minBy{it.path.size}
                else -> actualNext
            }
        }
        GossipValue(result.best, initial, result.path + localId)
    }.best
}
