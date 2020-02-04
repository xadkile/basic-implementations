package graph

/***
 * this by default is single-directional
 */
interface Edge<NT : Comparable<NT>, N : Node<NT>> {
    fun getFrom(): N
    fun getTo(): N
}

interface Weighted<T : Comparable<T>> {
    fun getWeight(): T
}

interface WeightedEdge<
        NT : Comparable<NT>,
        N : Node<NT>,
        W : Comparable<W>> :
    Edge<NT, N>,
    Weighted<W> {
}

/**
 * single direction, non-weighted edge
 */
class SimpleEdge<NT : Comparable<NT>, N : Node<NT>>(private val node1: N, private val node2: N) : Edge<NT, N> {
    override fun getFrom(): N {
        return this.node1
    }

    override fun getTo(): N {
        return this.node2
    }
}