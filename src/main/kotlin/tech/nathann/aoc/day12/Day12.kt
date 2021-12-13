package tech.nathann.aoc.day12

import tech.nathann.aoc.Input

var inputData = Input.getInputLinesWeb(12)

fun main() {
    val map = HashMap<String, MutableList<String>>()

    inputData.map {
        val arr = it.split("-")
        map.getOrPut(arr[0], ::arrayListOf).add(arr[1])
        map.getOrPut(arr[1], ::arrayListOf).add(arr[0])
    }
    println(map.numPaths(1))
    println(map.numPaths(2))
}

fun Map<String, List<String>>.numPaths(part: Int, current: String = "start", placesVisited: MutableList<String> = ArrayList()): Int {
    if(placesVisited.numDupes() >= part) return 0
    if(current == "end") return 1

    val options = this[current]!!

    val copy = ArrayList(placesVisited)
    if (!current[0].isUpperCase()) {
        copy.add(current)
    }

    return options
        .filter { it != "start" }
        .sumOf { numPaths(part, it, copy) }
}

fun <E> MutableList<E>.numDupes() = this.size - distinct().size
