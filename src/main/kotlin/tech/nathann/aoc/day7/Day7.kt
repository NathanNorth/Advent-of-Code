package tech.nathann.aoc.day7

import tech.nathann.aoc.Input
import kotlin.math.absoluteValue

val inputData = Input.getInputLinesWeb(7)

//5:06
fun main() {
    println(part1())
    println(part2())
}

fun part1(): Int {
    val nums = inputData[0].split(",").map { it.toInt() }.sorted()
    val median = nums[nums.size / 2]
    return nums.sumOf { (it - median).absoluteValue }
}
fun part2(): Int {
    val nums = inputData[0].split(",").map { it.toInt() }.toList()
    val sols = (0..(nums.maxOrNull() ?: error("no elements")))
        .map { index ->
            nums.map { (it - index).absoluteValue }
                .map { it.inc() * it / 2 }
                .sum() }
        .minByOrNull { it }
    return sols ?: error("no solutions")
}