package sort

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class SorterTest {
    val input = arrayOf(3,5,1,2,3,6,7)
    val ascendingExpectation = arrayOf(1,2,3,3,5,6,7)
    val descendingExpectation = ascendingExpectation.reversed().toTypedArray()
    @Test
    fun bubbleSort() {
        val asc = BubbleSort<Int>().sort(input,true)
        val desc = BubbleSort<Int>().sort(input,false)
        testArrayEqual(ascendingExpectation,asc)
        testArrayEqual(descendingExpectation,desc)
    }

    private fun testArrayEqual(expectation:Array<Int>, actual:Array<Int>){
        assertEquals(expectation.size,actual.size)
        for(x in expectation.indices){
            assertEquals(expectation[x],actual[x])
        }
    }
}