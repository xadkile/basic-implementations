package graph


/**
 * Immutable Graph
 * Hold edge direction info
 * Provide traversal and search operations (depth and breadth)
 */
interface Graph<NT : Comparable<NT>> {

    /**
     * check if there is an edge between two node
     */
    fun hasEdge(node1: Node<NT>, node2: Node<NT>): Boolean

    /**
     * get edge between two node, return null if no edge exist for the input pair
     */
    fun getEdge(node1: Node<NT>, node2: Node<NT>): Edge<NT>?

    /**
     * Get neighbor nodes of a node
     */
    fun getNeighbors(node: Node<NT>): List<Node<NT>>

    /**
     * Get edges of a node
     */
    fun getEdges(node:Node<NT>):List<Edge<NT>>

    /**
     * traversal by depth first search
     */
    fun depthTraversal(startingNode: Node<NT>, sideEffect: (node: Node<NT>) -> Unit)

    /**
     * search by depth-first search with a specified starting node
     */
    fun depthSearch(startingNode: Node<NT>, target: NT): Node<NT>?

    /**
     * search by depth-first search
     */
    fun depthSearch(target: NT): Node<NT>?

    /**
     * traversal by breadth first search
     */
    fun breadthTraversal(startingNode: Node<NT>, sideEffect: (node: Node<NT>) -> Unit)

    /**
     * search by bread first search, with a specified starting node
     */
    fun breadthSearch(startingNode:Node<NT>,target: NT): Node<NT>?

    /**
     * search by breadth first search
     */
    fun breadthSearch(target: NT): Node<NT>?

    /**
     * find the shortest path from [startNode] to [endNode] using breadth first search
     */
    fun shortestPath(startNode: Node<NT>, endNode: Node<NT>): List<Node<NT>>
}

/**
 * Mutable graph
 */
interface MutableGraph<NT : Comparable<NT>, N : Node<NT>,EDGE : Edge<NT>> : Graph<NT> {
    fun removeVertex(node: Node<NT>)
    fun addEdge(edge: EDGE)
    fun removeEdge(node1: Node<NT>, node2: Node<NT>)
}



