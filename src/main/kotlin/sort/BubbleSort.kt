package sort

fun main(args: Array<String>): Unit {
    for (x in 0..10) println(x)
}

class BubbleSort<T : Comparable<T>> : Sorter<T> {

    /**
     * O(n^2)
     */
    override fun sort(arr: Array<T>, ascending: Boolean): Array<T> {
        val arrClone = arr.clone()
        while(this.pass(arrClone,ascending)!=0){}
        return arrClone
    }

    private fun swap(arr: Array<T>, index1: Int, index2: Int): Array<T> {
        val v1: T = arr[index1]
        val v2: T = arr[index2]
        arr.set(index1, v2)
        arr.set(index2, v1)
        return arr
    }

    /**
     * sort in one pass
     */
    private fun pass(arr: Array<T>, ascending: Boolean): Int {
        var swapCount = 0
        for (x in arr.indices) {
            if (x < arr.size - 1) {
                val nextIndex = x + 1
                val v1 = arr[x]
                val v2 = arr[nextIndex]
                if (ascending) {
                    if (v1 > v2) {
                        this.swap(arr,x,nextIndex)
                        swapCount++
                    }
                }else{
                    if(v1<v2){
                        this.swap(arr,x,nextIndex)
                        swapCount++
                    }
                }

            }
        }
        return swapCount
    }
}