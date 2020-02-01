package graph


/**
 * Immutable Graph
 */
interface Graph<NT : Comparable<NT>, N : Node<NT>, EDGE : Edge<NT, N>> {
    fun hasEdge(node1: Node<NT>, node2: Node<NT>): Boolean
    fun getEdge(node1: Node<NT>, node2: Node<NT>): EDGE?
    fun getNeighbors(node: Node<NT>): List<Node<NT>>

    /**
     * traversal by depth first search
     */
    fun <R> depthTraversal(fromNode: Node<NT>, todo: (node: Node<NT>) -> R)

    /**
     * search by depth-first search
     */
    fun depthSearch(target: NT): Node<NT>

    /**
     * traversal by breadth first search
     */
    fun <R> breadTraversal(fromNode: Node<NT>, todo: (node: Node<NT>) -> R)

    /**
     * search by breadth first search
     */
    fun breadthSearch(target: NT): Node<NT>

    /**
     * find the shortest path from [fromNode] to [toNode] using breadth first search
     */
    fun shortestPath(fromNode: Node<NT>, toNode: Node<NT>): List<Node<NT>>
}

///**
// * Mutable graph
// */
//interface MutableGraph<NT : Comparable<NT>, N : Node<NT>> : Graph<NT,N> {
//    fun removeVertex(node: Node<NT>): Graph<NT, N>
//    fun addEdge(edge: DirectedEdge<NT, N>): Graph<NT, N>
//    fun removeEdge(node1: Node<NT>, node2: Node<NT>): Graph<NT, N>
//}



