package tech.nathann.aoc.day11

import tech.nathann.aoc.Input

var inputData = Input.getInputLinesWeb(11)

fun main() {
    val arr = inputData.map { it.map { it.digitToInt() }.toMutableList() }.toMutableList()

    for(day in 1..1000) {
        println("Step #$day")
        for(r in arr.indices) {
            for(c in arr[0].indices) {
                arr[r][c]++
            }
        }
        for(r in arr.indices) {
            for(c in arr[0].indices) {
                if(arr[r][c] > 9) {
                    arr.flash(r, c)
                }
            }
        }
        for(r in arr.indices)
            for(c in arr[0].indices)
                if(arr[r][c] == -1) arr[r][c] = 0

        if(arr.flatten().all { it == 0 }) {
            println(day)
            break
        }
    }
    for (i in arr.indices) {
        println(arr[i].toList().toString())
    }
    println(flashes)
}

var flashes = 0

fun MutableList<MutableList<Int>>.getValidNeighborsDiag(r: Int, c: Int) =
    listOf(r + 1 to c, r - 1 to c, r to c + 1, r to c - 1, r + 1 to c + 1, r - 1 to c + 1, r + 1 to c - 1, r - 1 to c - 1)
        .filter { (x, y) -> x >= 0 && x < this.size && y >= 0 && y < this[0].size }

fun MutableList<MutableList<Int>>.flash(r: Int, c: Int) {
    if(this[r][c] == -1) return
    flashes++
    this[r][c] = -1
    for (i in this.indices) {
        println(this[i].toList().toString())
    }
    println()
    this.getValidNeighborsDiag(r, c)
        .filter {  (r, c) -> this[r][c] != -1 }
        .map { (r, c) -> this[r][c]++ }
    this.getValidNeighborsDiag(r, c)
        .filter { (r, c) -> this[r][c] > 9 }
        .map { (r, c) -> flash(r, c) }
}