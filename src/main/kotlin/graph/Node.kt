package graph

import java.util.*

/**
 * Hold comparable value
 * [id]: unique identifier
 * [value]: value hold by [Node]
 */
data class Node<T : Comparable<T>> (val value:T,val id:UUID){
    constructor(value:T):this(value,UUID.randomUUID())
}
