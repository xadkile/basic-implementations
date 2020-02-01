package graph

fun main(){
    println("zxczxcxzc")
}
data class AdjacencyListGraph<
        NT : Comparable<NT>,
        N : Node<NT>,
        EDGE:Edge<NT,N>> (val adjacencyList: Map<Node<NT>, Map<Node<NT>, EDGE>>) :
    Graph<NT, N, EDGE> {

    override fun hasEdge(node1: Node<NT>, node2: Node<NT>): Boolean {
        return this.getEdge(node1, node2) != null
    }

    override fun getEdge(node1: Node<NT>, node2: Node<NT>): EDGE? {
        return this.adjacencyList[node1]?.get(node2)
    }

    override fun getNeighbors(node: Node<NT>): List<Node<NT>> {
        return this.adjacencyList[node]?.keys?.toList() ?: listOf()
    }

    override fun <R> depthTraversal(fromNode: Node<NT>, todo: (node: Node<NT>) -> R) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun depthSearch(target: NT): Node<NT> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <R> breadTraversal(fromNode: Node<NT>, todo: (node: Node<NT>) -> R) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun breadthSearch(target: NT): Node<NT> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun shortestPath(fromNode: Node<NT>, toNode: Node<NT>): List<Node<NT>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


//    override fun removeVertex(node: Node<NT>): Graph<NT, N> {
//        //remove node and all edges from this node
//        val newMatrix = this.adjacencyList - node
//        //remove all edge point to this node
//        val nm2 = newMatrix.mapValues { entry ->
//            val value = entry.value - node
//            value
//        }
//        return AdjacencyListGraph(nm2)
//    }

//    override fun addEdge(edge: Edge<NT, N>): Graph<NT, N> {
//        val list = this.adjacencyList.getOrDefault(edge.getFrom(), mapOf())
//        //add edge to list
//        val newList = list + (edge.getTo() to edge)
//        val newAdjacencylist = this.adjacencyList - edge.getFrom() + (edge.getFrom() to newList)
//        return AdjacencyListGraph(newAdjacencylist)
//    }

//    override fun removeEdge(node1: Node<NT>, node2: Node<NT>): Graph<NT, N> {
//        val newData: Map<Node<NT>, Edge<NT, N>>? = this.adjacencyList[node1]?.let {
//            it - node2
//        }
//        return if (newData == null) {
//            this
//        } else {
//            if (newData.isEmpty()) {
//                AdjacencyListGraph(this.adjacencyList - node1)
//            } else {
//                AdjacencyListGraph((this.adjacencyList - node1) + (node1 to newData))
//            }
//        }
//    }

}