data class GossipValue<ID, Type>(val value: Type, val path: List<ID>)

fun <ID, Type> Aggregate<ID>.firstGossip(
    initial: Type,
    selector: (Type, Type) -> Boolean,
): Type {
    val local = GossipValue(initial, listOf(localId))
    return share(local) { gossip ->
        gossip.fold(local){ current, next ->
            when {
                selector(current.value, next.value) || localId in next.path -> current
                else -> next.copy(path=next.path+localId)
            }
        }
    }.value
}
