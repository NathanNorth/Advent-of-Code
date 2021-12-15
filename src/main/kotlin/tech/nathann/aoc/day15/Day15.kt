package tech.nathann.aoc.day15

import tech.nathann.aoc.Input
import tech.nathann.aoc.day9.getValidNeighbors

var inputData = Input.getInputLinesWeb(15)

fun main() {
    val arr = inputData.map { it.map { it.digitToInt() }.toMutableList() }.toMutableList()

    println(arr.bestPath())
}
val found = mutableListOf<Int>()
fun MutableList<MutableList<Int>>.bestPath(count: Int = -this[0][0], r: Int = 0, c: Int = 0, visited: Collection<Pair<Int, Int>> = mutableListOf()): Int {
    if(count > (found.minOrNull() ?: Int.MAX_VALUE)) return Int.MAX_VALUE
    if(visited.contains(r to c)) return Int.MAX_VALUE
    if(r == this.size - 1 && c == this[0].size - 1) {
        println("Path #${found.size} found!")
        val num = count + this[r][c]
        found.add(num)
        return num
    }
    val newVisited = visited + (r to c)
    return getValidNeighbors(r, c)
        .filter { (nR, nC) -> nR > r || nC > c }
        .minOf { (friendR, friendC) -> bestPath(count + this[r][c], friendR, friendC, newVisited) }
}