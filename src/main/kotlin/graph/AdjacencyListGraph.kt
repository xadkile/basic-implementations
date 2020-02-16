package graph

import java.util.*


fun main() {

}

/**
 * Use a 2-layer nested map to represent an adjacency list and to allow instant lookup
 */
data class AdjacencyListGraph<
        NT : Comparable<NT>>(val adjacencyList: Map<out Node<NT>, Map<out Node<NT>, Edge<NT>>>) :
    Graph<NT> {

    private val defaultStartingNode: Node<NT>? = run {
        val result: Result<Node<NT>> = runCatching { this.adjacencyList.keys.first() }
        result.getOrNull()
    }

    override fun hasEdge(node1: Node<NT>, node2: Node<NT>): Boolean {
        return this.getEdge(node1, node2) != null
    }

    override fun getEdge(node1: Node<NT>, node2: Node<NT>): Edge<NT>? {
        return this.adjacencyList[node1]?.get(node2)
    }

    override fun getNeighbors(node: Node<NT>): List<Node<NT>> {
        return this.adjacencyList[node]?.keys?.toList() ?: listOf()
    }

    override fun getEdges(node: Node<NT>): List<Edge<NT>> {
        return this.adjacencyList[node]?.values?.toList() ?: listOf()
    }

    /**
     * [startingNode]: starting node
     * [sideEffect]: callback to run on each node
     * non-recursive approach
     */
    override fun depthTraversal(startingNode: Node<NT>, sideEffect: (node: Node<NT>) -> Unit) {
        this.breakableDepthTraversal(
            startingNode = startingNode,
            breakCondition = { false },
            returnOnBreak = { null },
            sideEffect = sideEffect
        )
    }

    private fun <R> breakableDepthTraversal(
        startingNode: Node<NT>,
        breakCondition: (node: Node<NT>) -> Boolean,
        returnOnBreak: (node: Node<NT>) -> R,
        sideEffect: (node: Node<NT>) -> Unit
    ): R? {
        //LIFO
        val stack = Stack<Node<NT>>()
        stack.add(startingNode)
        //list of visited node, use map for instant lookup
        val visited = mutableMapOf<Node<NT>, Node<NT>>()

        while (stack.isNotEmpty()) {
            val currentNode: Node<NT> = stack.pop()
            //marked as visited
            if (visited[currentNode] == null) {
                visited[currentNode] = currentNode
                sideEffect(currentNode)
                if (breakCondition(currentNode)) {
                    return returnOnBreak(currentNode)
                }
            }
            //add neighbours to stack
            val edgeList: Map<out Node<NT>, Edge<NT>>? = this.adjacencyList[currentNode]
            if (edgeList != null) {
                for (nb in edgeList.keys) {
                    if (visited[nb] == null) stack.push(nb)
                }
            }
        }
        return null
    }

    override fun depthSearch(startingNode: Node<NT>, target: NT): Node<NT>? {
        return this.breakableDepthTraversal(
            startingNode = startingNode,
            breakCondition = { node: Node<NT> -> node.getValue() == target },
            returnOnBreak = { it },
            sideEffect = {}
        )
    }

    override fun depthSearch(target: NT): Node<NT>? {
        return this.defaultStartingNode?.let {
            this.depthSearch(it, target)
        }
    }

    private fun <R> breakableBreadthTraversal(
        startingNode: Node<NT>,
        breakCondition: (node: Node<NT>) -> Boolean,
        returnOnBreak: (node: Node<NT>, prevNode: Node<NT>, prevMap: Map<Node<NT>, Node<NT>>) -> R,
        sideEffect: (node: Node<NT>) -> Unit
    ): R? {
        //FIFO
        val queue: Queue<Node<NT>> = LinkedList<Node<NT>>()
        val visited: MutableMap<Node<NT>, Node<NT>> = mutableMapOf()
        var prevNode: Node<NT>? = null
        val prevMap: MutableMap<Node<NT>, Node<NT>> = mutableMapOf()
        queue.add(startingNode)
        while (queue.isNotEmpty()) {
            val currentNode: Node<NT> = queue.poll()
            //mark the current node as visited
            if (visited[currentNode] == null) {
                visited[currentNode] = currentNode
                sideEffect(currentNode)
                prevNode = currentNode
                if (breakCondition(currentNode)) {
                    return returnOnBreak(currentNode, prevNode, prevMap)
                }
            }
            //add neighbours to the queue
            val neighbours: Map<out Node<NT>, Edge<NT>>? = this.adjacencyList[currentNode]
            if (neighbours != null) {
                for (nb in neighbours.keys) {
                    if (visited[nb] == null) {
                        queue.offer(nb)
//                        val oldPrevNodes:List<Node<NT>> = prevMap.computeIfAbsent(nb, { listOf()})
//                        val newPrevNodes = oldPrevNodes + currentNode
//                        prevMap[nb]= newPrevNodes
                        prevMap.putIfAbsent(nb,currentNode)
                    }
                }
            }
        }
        return null
    }

    override fun breadthTraversal(startingNode: Node<NT>, sideEffect: (node: Node<NT>) -> Unit) {
        this.breakableBreadthTraversal(
            startingNode = startingNode,
            breakCondition = { false },
            returnOnBreak = { _, _, _ -> Unit },
            sideEffect = sideEffect
        )
    }

    override fun breadthSearch(target: NT): Node<NT>? {
        return this.defaultStartingNode?.let {
            this.breadthSearch(it, target)
        }
    }

    override fun breadthSearch(startingNode: Node<NT>, target: NT): Node<NT>? {
        return this.breakableBreadthTraversal(
            startingNode = startingNode,
            breakCondition = { node: Node<NT> -> node.getValue() == target },
            returnOnBreak = { node, prevNode, prevMap -> node },
            sideEffect = {}
        )
    }

    /**
     * find shortest path between two nodes, using bread depth first
     * TODO make it return all equivalent shortest paths
     */
    override fun shortestPath(startNode: Node<NT>, endNode: Node<NT>): List<Node<NT>> {
        val result: List<Node<NT>>? = this.breakableBreadthTraversal(
            startingNode = startNode,
            breakCondition = { it == endNode },
            returnOnBreak = { targetNode: Node<NT>,
                              prevNode: Node<NT>,
                              prevMap: Map<Node<NT>, Node<NT>> ->
                val path = mutableListOf<Node<NT>>(targetNode)
                var previousNode: Node<NT>? = prevMap[targetNode]
                while (previousNode != null) {
                    path.add(previousNode)
                    previousNode = prevMap[previousNode]
                }
                path.reversed()
            },
            sideEffect = {}
        )
        return result ?: listOf()
    }
}