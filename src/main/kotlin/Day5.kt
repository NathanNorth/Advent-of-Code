import tech.nathann.aoc.Input

val inputData = Input.getInputLinesWeb(5)

fun main() {
    println(sol(1))
    println(sol(2))
    println(solSane(1))
    println(solSane(part = 2))
}
//abuse the zip function to write a very short solution block
fun sol(part: Int): Int {
    val sums = Array(1000) {IntArray(1000)}
    inputData
        .map { Line(it) }
        .filter { if(part == 1) it.x1 == it.x2 || it.y1 == it.y2 else true }
        .map {
            it.x1.rangeToGood(it.x2).zipOrPair(it.y1.rangeToGood(it.y2)).map { (x, y) ->
                sums[y][x]++
            }
        }
    return sums.flatMap { arr -> arr.map { it >= 2 } }.count {it}
}
//solve this a more reasonable way
fun solSane(part: Int): Int {
    val sums = Array(1000) {IntArray(1000)}
    inputData
        .map { Line(it) }
        .map {
            if(it.x1 == it.x2) {
                for(y in it.y1.rangeToGood(it.y2)) sums[y][it.x1]++
            }
            if(it.y1 == it.y2) {
                for(x in it.x1.rangeToGood(it.x2)) sums[it.y1][x]++
            }
            if(part == 2 && it.x1 != it.x2 && it.y1 != it.y2) {
                it.x1.rangeToGood(it.x2).zip(it.y1.rangeToGood(it.y2)).map { (x, y) ->
                    sums[y][x]++
                }
            }
        }
    return sums.flatMap { arr -> arr.map { it >= 2 } }.count {it}
}

//does what rangeTo really should do. creates an IntProgression that moves towards the end value, regardless of direciton
fun Int.rangeToGood(to: Int): Iterable<Int> = if(this < to) this..to else this downTo to

//scuffed zip abuse
fun <A, B> Iterable<A>.zipOrPair(iterable: Iterable<B>): List<Pair<A, B>> {
    if(iterable.toList().size == 1) return this.map { it to iterable.first()}
    if(this.toList().size == 1) return  iterable.map { this.first() to it }
    return this.zip(iterable)
}

//good data abstraction/parsing
data class Line(val input: String) {
    private val nums = input.split(Regex(",|( -> )"))
    val x1 = nums[0].toInt()
    val y1 = nums[1].toInt()
    val x2 = nums[2].toInt()
    val y2 = nums[3].toInt()
}