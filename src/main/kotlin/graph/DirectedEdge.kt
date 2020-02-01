package graph

/***
 * this by default is by-directional
 */
interface Edge<NT : Comparable<NT>, N : Node<NT>> {
    fun getFirst(): N
    fun getSecond(): N
}

interface Weighted<T : Comparable<T>> {
    fun getWeight(): T
}

interface Direction<NT : Comparable<NT>, N : Node<NT>> {
    fun getFrom(): N
    fun getTo(): N
}

interface DirectedEdge<
        NT : Comparable<NT>,
        N : Node<NT>> :
    Edge<NT, N>,
    Direction<NT, N>

interface WeightedEdge<
        NT : Comparable<NT>,
        N : Node<NT>,
        W : Comparable<W>> :
    Edge<NT, N>,
    Weighted<W>

interface WeightedDirectedEdge<
        NT : Comparable<NT>,
        N : Node<NT>,
        W : Comparable<W>> :
    Edge<NT, N>,
    Weighted<W>,
    Direction<NT, N>