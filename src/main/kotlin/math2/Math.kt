package math2

import kotlin.math.abs

fun main() {
}

fun squareRoot(x: Double, difLimit: Double): Double {
    var re = x/7
    var dif = (re * re - x) / (2 * re)
    var iterationCount = 0
    while (abs(dif) >= difLimit) {
        re -= dif
        dif = (re * re - x) / (2 * re)
        iterationCount++
    }
    println(iterationCount)
    return re
}

fun squareRoot(x: Number, difLimit: Double): Double {
    return squareRoot(x.toDouble(), difLimit)
}

fun squareRoot(x: Number): Double {
    return squareRoot(x, 0.00000000001)
}
