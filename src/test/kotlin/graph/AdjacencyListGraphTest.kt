package graph

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertNotNull

internal class AdjacencyListGraphTest {

    val nodeA = Node("A")
    val nodeB = Node("B")
    val nodeC = Node("C")
    val nodeD = Node("D")
    val nodeE = Node("E")
    val nodeF = Node("F")
    val nodeG = Node("G")

    val graph = AdjacencyListGraph<String, Node<String>, SimpleEdge<String, Node<String>>>(
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
            nodeD to mapOf(),
            nodeE to mapOf(
                nodeA to SimpleEdge(nodeE, nodeA),
                nodeF to SimpleEdge(nodeE, nodeF)
            ),
            nodeF to mapOf(
                nodeB to SimpleEdge(nodeF, nodeB),
                nodeE to SimpleEdge(nodeF, nodeE)
            )
        )
    )

    @Test
    fun hasEdge() {
        assertTrue(graph.hasEdge(nodeA, nodeB))
        assertFalse(graph.hasEdge(nodeA, nodeD))
    }

    @Test
    fun getEdge() {
        val edge: Edge<String, Node<String>>? = graph.getEdge(nodeB, nodeD)
        assertNotNull(edge)
        assertEquals(nodeB, edge.getFrom())
        assertEquals(nodeD,edge.getTo())
    }

    @Test
    fun getNeighbors() {
        val ne:List<Node<String>> = graph.getNeighbors(nodeE)
        val expectation = listOf(nodeA,nodeF)
        assertEquals(expectation,ne)
    }

    @Test
    fun depthTraversal() {
        val order = mutableListOf<String>()
        val expectation = mutableListOf(
            "A","E","F","B","D","C","G"
        )
        graph.depthTraversal(nodeA) {order.add(it.value)}
        assertEquals(expectation,order)
    }

    @Test
    fun depthSearch() {
        val data:String = nodeF.value
        val exepectation:Node<String> = nodeF
        val re:Node<String> = graph.depthSearch(data)
        assertEquals(exepectation,re)
    }

    @Test
    fun breadTraversal() {
    }

    @Test
    fun breadthSearch() {
    }

    @Test
    fun shortestPath() {
    }
}