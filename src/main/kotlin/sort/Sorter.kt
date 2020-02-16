package sort

interface Sorter <T:Comparable<T>>{
    /**
     * return a new sorted array
     */
    fun sort(arr:Array<T>, ascending:Boolean):Array<T>
}