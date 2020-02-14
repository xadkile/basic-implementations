package graph

import java.util.*

/**
 * Hold comparable value
 * [id]: unique identifier
 * [value]: value hold by [Node]
 */
data class SimpleNode<T : Comparable<T>>(private val _value: T, private val _id: UUID) : Node<T>, WithId<UUID> {

    constructor(value: T) : this(value, UUID.randomUUID())

    override fun getId(): UUID {
        return this._id
    }

    override fun getValue(): T {
        return this._value
    }
}

interface Node<T : Comparable<T>> {
    fun getValue(): T
}

interface WithId<ID : Comparable<ID>> {
    fun getId(): ID
}