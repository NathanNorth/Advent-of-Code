package tech.nathann.aoc.day9

import tech.nathann.aoc.Input

var inputData = Input.getInputLinesWeb(9)

fun main() {
    println(part1())
}
fun part1(): Int {
    val arr = inputData.map { it.toCharArray().toList().map { it.digitToInt() }.toMutableList() }.toMutableList()

    var list = arrayListOf<Int>()
    var returnable = 1
    for (x in 0 until arr.size) {
        for(y in 0 until arr[0].size) {
            if(arr.isLow(x, y) > 0) {
                //println("Testing basin size of " + x +" and " + y + ":" + arr.basinSize(x, y))
                list.add(arr.basinSize(x, y))
            }
        }
    }
    list.sort()
    for(i in list.size - 1 downTo list.size - 3) {
        returnable *= list[i]
    }

    return returnable
}

fun MutableList<MutableList<Int>>.isLow(x: Int, y: Int):Int {
    var pairs = listOf(x + 1 to y, x - 1 to y, x to y + 1, x to y - 1)
    var isLow = true
    for(pair in pairs) {
        if(pair.first >= 0 && pair.first < this.size) {
            if(pair.second >= 0 && pair.second < this[0].size) {
                isLow = isLow && this[x][y] < this[pair.first][pair.second]
            }
        }
    }

    if(isLow) {
        return 1 + this[x][y]
    }
    return 0
}

fun MutableList<MutableList<Int>>.basinSize(x: Int, y: Int): Int {
    if(x < 0 || x >= this.size) return 0
    if(y < 0 || y >= this[0].size) return 0
    if(this[x][y] == 9 || this[x][y] == -1) return 0
    this[x][y] = -1
    val neighbors = listOf(x + 1 to y, x - 1 to y, x to y + 1, x to y - 1).map { (x, y) -> basinSize(x, y) }.sum()
    return 1 + neighbors
}