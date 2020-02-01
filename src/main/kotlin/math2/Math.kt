package math

fun squareRoot(x:Double,iterationCount:Int):Double{
    var re = 1.0
    for(i in 0 until iterationCount){
        val dif = (re * re - x)/(2*re)
        println(dif)
        re -= dif
    }
    return re
}

fun squareRoot(x:Number,iterationCount: Int):Double{
    return squareRoot(x.toDouble(),iterationCount)
}

fun squareRoot(x:Number):Double{
    return squareRoot(x,10)
}