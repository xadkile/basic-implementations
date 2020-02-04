package dynamic_programming

import java.math.BigInteger

/**
 * O(n)
 */
fun fibonacciUsingLoop(n: Long): BigInteger {
    return when (n) {
        0L -> BigInteger.ZERO
        1L -> BigInteger.ONE
        else -> {
            var n0: BigInteger = BigInteger.ZERO
            var n1: BigInteger = BigInteger.ONE
            var accu: BigInteger = BigInteger.ZERO
            for (x in 2 until n) {
                accu = n0 + n1
                n1 = accu
                n0 = n1
            }
            accu
        }
    }
}

/**
 * calculate fibonacciWithCache number at n position
 * n start at 0
 * O(2n)
 */
fun fibonacciWithCache(n: Long, fCache: MutableMap<Long, BigInteger>): BigInteger {
    if (n == 0L) {
        return BigInteger.ZERO
    } else if (n == 1L) {
        return BigInteger.ONE
    } else {
        val b1: BigInteger = if (fCache.containsKey(n - 1)) {
            fCache.getOrDefault(n - 1, BigInteger.valueOf(-1))
        } else {
            val newVal = fibonacciWithCache(n - 1, fCache)
            fCache.put(n - 1, newVal)
            newVal
        }

        val b2: BigInteger = if (fCache.containsKey(n - 2)) {
            fCache.getOrDefault(n - 2, BigInteger.valueOf(-1))
        } else {
            val newVal = fibonacciWithCache(n - 2, fCache)
            fCache.put(n - 2, newVal)
            newVal
        }
        return b1 + b2
    }
}

/**
 * O(n^2)
 */
fun recursiveFinonacci(n: Long): BigInteger {
    return if (n == 0L) {
        BigInteger.ZERO
    } else if (n == 1L) {
        BigInteger.ONE
    } else {
        recursiveFinonacci(n - 1) + recursiveFinonacci(n - 2)
    }
}