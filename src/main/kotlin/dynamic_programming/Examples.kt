package dynamic_programming


fun fibonacciLinear2(n: Int): Long {
    if (n == 0) {
        return 0
    }
    if (n == 1) {
        return 1
    }

    var n0: Long = 0
    var n1: Long = 1
    var accu: Long=0

    for (x in 2 until n) {
        accu = n0 + n1
        n1 = accu
        n0 = n1
    }
    return accu
}

fun fibonacciLinear(n: Int): Long {
    val arr: LongArray = LongArray(n + 1)
    arr[0] = 0
    arr[1] = 1
    for (x in 2..n) {
        arr[x] = arr[x - 1] + arr[x - 2]
    }

    return arr[n]
}

/**
 * calculate fibonacciWithCache number at n position
 * n start at 0
 */
fun fibonacciWithCache(n: Long, fCache: MutableMap<Long, Long>): Long {
    if (n == 0L) {
        return 0
    } else if (n == 1L) {
        return 1
    } else {

        val b1 = if (fCache.containsKey(n - 1)) {
            val i = fCache[n - 1]
            if (i != null) {
                i
            } else {
                -1
            }
        } else {
            val newVal = fibonacciWithCache(n - 1, fCache)
            fCache.put(n - 1, newVal)
            newVal
        }

        val b2 = if (fCache.containsKey(n - 2)) {
            val i = fCache[n - 2]
            if (i != null) {
                i
            } else {
                -1
            }
        } else {
            val newVal = fibonacciWithCache(n - 2, fCache)
            fCache.put(n - 2, newVal)
            newVal
        }
        return b1 + b2
    }
}

fun recursiveFinonacci(n: Long): Long {
    return if (n == 0L) {
        0
    } else if (n == 1L) {
        1
    } else {
        recursiveFinonacci(n - 1) + recursiveFinonacci(n - 2)
    }
}