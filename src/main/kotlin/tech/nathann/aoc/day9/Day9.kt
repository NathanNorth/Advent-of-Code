package tech.nathann.aoc.day9

import tech.nathann.aoc.Input

var inputData = Input.getInputLinesWeb(9)

fun main() {
    println(part1())
}
fun part1(): Int {
    val arr = inputData.map { it.toCharArray().toList().map { it.digitToInt() }.toMutableList() }.toMutableList()
    return arr
        .filter { (x, y) -> arr.isLow(x, y) > 0 }
        .map { (x, y) -> arr.basinSize(x, y) }
        .sorted()
        .reversed()
        .subList(0, 3)
        .fold(initial = 1) {base, it -> base * it}
}

fun MutableList<MutableList<Int>>.isLow(x: Int, y: Int):Int {
    val isLow = getValidNeighbors(x, y)
        .map { (xAdjacent, yAdjacent) -> this[x][y] < this[xAdjacent][yAdjacent]}
        .fold(initial = true) {base, it -> base && it}

    if(isLow) {
        return 1 + this[x][y]
    }
    return 0
}

fun MutableList<MutableList<Int>>.getValidNeighbors(x: Int, y: Int) =
    listOf(x + 1 to y, x - 1 to y, x to y + 1, x to y - 1)
        .filter { (x, y) -> x >= 0 && x < this.size && y >= 0 && y < this[0].size }

fun MutableList<MutableList<Int>>.basinSize(x: Int, y: Int): Int {
    if(this[x][y] == 9 || this[x][y] == -1) return 0
    this[x][y] = -1
    val neighbors = getValidNeighbors(x, y).map { (x, y) -> basinSize(x, y) }.sum()
    return 1 + neighbors
}