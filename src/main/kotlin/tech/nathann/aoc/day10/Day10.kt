package tech.nathann.aoc.day10

import tech.nathann.aoc.Input
import java.util.*

var inputData = Input.getInputLinesWeb(10)

fun main() {
    println(part1())
    println(part2())
}
fun part1(): Int {
    return inputData.map { getCorrupt(it) }.sum()
}
fun part2(): Long {
    val filtered =  inputData.filter { getCorrupt(it) == 0 }

    return filtered.map { completionScore(it) }
        .sorted()[filtered.size / 2]
}

val completionScore = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)
fun completionScore(str: String): Long {
    val stack = Stack<Char>()
    for(c in str) {
        val isOpen = opens.any { it  == c }
        if(isOpen) {
            stack.push(c)
        }
        else {
            stack.pop()
        }
    }
    var score = 0L
    while(!stack.empty()) {
        score *= 5
        score += completionScore[openToClose[stack.pop()]] ?: error("Mapping")
    }
    return score
}

val opens = listOf('(', '[', '{', '<')
val closes = listOf(')', ']', '}', '>')
val openToClose = opens.zip(closes).toMap()
val corruptScore = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
fun getCorrupt(str: String): Int {
    val stack = Stack<Char>()
    for(c in str) {
        val isOpen = opens.any { it  == c }
        if(isOpen) {
            stack.push(c)
        }
        else {
            val isRight = openToClose[stack.peek()] == c
            if(isRight) stack.pop()
            else return corruptScore[c] ?: error("Mapping")
        }
    }
    return 0
}