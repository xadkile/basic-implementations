package graph

/**
 * Hold comparable value
 */
interface Node<T : Comparable<T>> {
    fun getValue(): T
}
