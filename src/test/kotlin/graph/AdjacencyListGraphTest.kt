package graph

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertNotNull

internal class AdjacencyListGraphTest {

    val nodeA = SimpleNode("A")
    val nodeB = SimpleNode("B")
    val nodeC = SimpleNode("C")
    val nodeD = SimpleNode("D")
    val nodeE = SimpleNode("E")
    val nodeF = SimpleNode("F")
    val nodeG = SimpleNode("G")
    val nodeList = listOf(nodeA, nodeB, nodeC, nodeD, nodeE, nodeF, nodeG)
    val graph = AdjacencyListGraph(
        mapOf(
            nodeA to mapOf(
                nodeB to SimpleEdge(nodeA, nodeB),
                nodeC to SimpleEdge(nodeA, nodeC),
                nodeE to SimpleEdge(nodeA, nodeE)
            ),
            nodeB to mapOf(
                nodeA to SimpleEdge(nodeB, nodeA),
                nodeF to SimpleEdge(nodeB, nodeF),
                nodeD to SimpleEdge(nodeB, nodeD)
            ),
            nodeC to mapOf(
                nodeA to SimpleEdge(nodeC, nodeA),
                nodeG to SimpleEdge(nodeC, nodeG)
            ),
            nodeD to mapOf(
                nodeB to SimpleEdge(nodeD, nodeB)
            ),
            nodeE to mapOf(
                nodeA to SimpleEdge(nodeE, nodeA),
                nodeF to SimpleEdge(nodeE, nodeF)
            ),
            nodeF to mapOf(
                nodeB to SimpleEdge(nodeF, nodeB),
                nodeE to SimpleEdge(nodeF, nodeE)
            ),
            nodeG to mapOf(
                nodeC to SimpleEdge(nodeG, nodeC)
            )
        )
    )

    @Test
    fun hasEdge() {
        assertTrue(this.graph.hasEdge(this.nodeA, this.nodeB))
        assertFalse(this.graph.hasEdge(this.nodeA, this.nodeD))
    }

    @Test
    fun getEdge() {
        val edge: Edge<String>? = this.graph.getEdge(this.nodeB, this.nodeD)
        assertNotNull(edge)
        assertEquals(this.nodeB, edge.getFrom())
        assertEquals(this.nodeD, edge.getTo())
    }

    @Test
    fun getNeighbors() {
        val ne: List<Node<String>> = this.graph.getNeighbors(nodeE)
        val expectation = listOf(this.nodeA, this.nodeF)
        assertEquals(expectation, ne)
    }

    @Test
    fun depthTraversal() {
        val order = mutableListOf<String>()
        val expectation = mutableListOf(
            "A", "E", "F", "B", "D", "C", "G"
        )
        this.graph.depthTraversal(this.nodeA) { order.add(it.getValue()) }
        assertEquals(expectation, order)
    }

    @Test
    fun depthSearch() {
        this.testSearchWithDefaultStartingNode { data: String -> this.graph.depthSearch(data) }
        this.testSearchWithCustomStartingNode { startingNode: Node<String>, data: String ->
            this.graph.depthSearch(startingNode, data)
        }
    }

    @Test
    fun breadthTraversal() {
        val order = mutableListOf<String>()
        val expectation = mutableListOf("A", "B", "C", "E", "F", "D", "G")
        this.graph.breadthTraversal(nodeA) { it ->
            order.add(it.getValue())
            Unit
        }
        assertEquals(expectation, order)
    }

    @Test
    fun breadthSearch() {
        this.testSearchWithDefaultStartingNode { data: String -> this.graph.breadthSearch(data) }
        this.testSearchWithCustomStartingNode { startingNode: Node<String>, data: String ->
            this.graph.breadthSearch(startingNode, data)
        }
    }

    private fun testSearchResult(re: Node<String>?, expectation: Node<String>) {
        assertNotNull(re)
        assertEquals(expectation, re)
    }

    private fun testSearchWithDefaultStartingNode(searchFunction: (data: String) -> Node<String>?) {
        val data: String = this.nodeF.getValue()
        val expectation: Node<String> = this.nodeF
        val re: Node<String>? = searchFunction(data)
        testSearchResult(re, expectation)
    }

    private fun testSearchWithCustomStartingNode(searchFunction: (startingNode: Node<String>, data: String) -> Node<String>?) {
        val data: String = this.nodeF.getValue()
        val expectation: Node<String> = this.nodeF
        for (node: Node<String> in nodeList) {
            val rr: Node<String>? = searchFunction(node, data)
            this.testSearchResult(rr, expectation)
        }
    }

    @Test
    fun shortestPath() {
        val target:Node<String> = nodeF
        val expectation = listOf(nodeA,nodeB,nodeF)
        val result = this.graph.shortestPath(nodeA,target)
        assertEquals(expectation,result)
    }
}