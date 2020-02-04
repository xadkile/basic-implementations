package graph


/**
 * Immutable Graph
 * Hold edge direction info
 * Provide traversal and search operations (depth and breadth)
 */
interface Graph<NT : Comparable<NT>, N : Node<NT>, EDGE : Edge<NT, N>> {

    /**
     * check if there is an edge between two node
     */
    fun hasEdge(node1: Node<NT>, node2: Node<NT>): Boolean

    /**
     * get edge between two node, return null if no edge exist for the input pair
     */
    fun getEdge(node1: Node<NT>, node2: Node<NT>): EDGE?

    /**
     * Get neighbor nodes of a node
     */
    fun getNeighbors(node: Node<NT>): List<Node<NT>>

    /**
     * Get edges of a node
     */
    fun getEdges(node:Node<NT>):List<EDGE>

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

/**
 * Mutable graph
 */
interface MutableGraph<NT : Comparable<NT>, N : Node<NT>,EDGE : Edge<NT, N>> : Graph<NT,N,EDGE> {
    fun removeVertex(node: Node<NT>)
    fun addEdge(edge: EDGE)
    fun removeEdge(node1: Node<NT>, node2: Node<NT>)
}



