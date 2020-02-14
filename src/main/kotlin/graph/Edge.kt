package graph

/***
 * single-directional edge
 */
interface Edge<NT : Comparable<NT>> {
    fun getFrom(): Node<NT>
    fun getTo(): Node<NT>
}

interface Weighted<T : Comparable<T>> {
    fun getWeight(): T
}

interface WeightedEdge<
        NT : Comparable<NT>,
        WEIGHT : Comparable<WEIGHT>> :
    Edge<NT>,
    Weighted<WEIGHT> {
}

/**
 * single direction, non-weighted edge
 */
class SimpleEdge<NT : Comparable<NT>>
    (private val node1: Node<NT>,
     private val node2: Node<NT>) : Edge<NT> {
    override fun getFrom(): Node<NT> {
        return this.node1
    }

    override fun getTo(): Node<NT> {
        return this.node2
    }
}