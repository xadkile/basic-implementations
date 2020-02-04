package math2

import org.junit.jupiter.api.Test
import kotlin.random.Random
import math2.squareRoot
import org.junit.jupiter.api.Assertions.assertEquals

internal class MathKtTest {
    @Test
    fun squareRoot() {
        val input = Random.nextDouble()*10000
        val result = squareRoot(input)
        assertEquals(input,result*result,0.0001)
    }
}