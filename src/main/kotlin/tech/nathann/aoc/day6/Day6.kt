package tech.nathann.aoc.day6

import tech.nathann.aoc.Input

val inputData = Input.getInputLinesWeb(6)

fun main() {
    println(doTheThing(256))
    //println(doTheThing(256))
}
fun doTheThing(days: Int): Long {
    val fishes = inputData[0].split(",").map { it.toInt() }
    var numFishes = Array(9) {0L}
    for(fish in fishes) {
        numFishes[fish]++
    }
    for(days in 0 until days) {
        println(days)
        val newFishes = Array(9) {0L}
        newFishes[6] = numFishes[0] + numFishes[7]
        newFishes[7] = numFishes[8]
        newFishes[8] = numFishes[0]
        for(i in 0 until 6) {
            newFishes[i] = numFishes[i + 1]
        }

        numFishes = newFishes
    }
    return numFishes.sum()
    //1859990519
}