package tech.nathann.aoc.day3

import tech.nathann.aoc.Input

val inputData = Input.getInputLinesWeb(3)

fun main() {
    fun sol(xor: Boolean) = (0 until inputData[0].length).toList()
        .map { index ->
            inputData.count { it[index] == '0' }
        }.map {it > inputData.size - it }
        .map { it.xor(xor) }
        .joinToString("") {
            asString(it)
        }
        .toInt(radix = 2)
    println(sol(true) * sol(false))
}
fun asString(it: Boolean): String {
    return if(it) "1" else "0"
}