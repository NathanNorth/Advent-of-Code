package tech.nathann.aoc.day8

import tech.nathann.aoc.Input

val inputData = Input.getInputLinesWeb(8)

fun main() {
    println(part1())
    println(part2())
}
fun part1(): Int {
    val flatList = inputData.toList().map { it.split("|")[1].split(" ") }.flatten()
    return flatList.count {
        it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7
    }
}
fun part2(): Int {
    val data = inputData.map { it.split(" | ")[0].split(" ")
        .map { it.toCharArray().sorted().joinToString("") }
    }
    val output = inputData.map { it.split(" | ")[1].split(" ")
        .map { it.toCharArray().sorted().joinToString("") }
    }

    return data.zip(output).map { (data, output) -> Line(data, output) }.sumOf { it.sum }
}
data class Line(val data: List<String>, val output: List<String>) {
    val decode = findValidDecoderFor(data)
    val sum = output.map { decode.decode(it).toString() }.joinToString("").toInt()
}

fun findValidDecoderFor(data: List<String>): Decoder = allDecoders
        .first { decoder -> data.map { decoder.decode(it) != null }.all { it } } //find first decoder which doesn't fail

//warning: internet code
fun String.permute(str: String = ""): List<String> =
    if(isEmpty()) listOf(str) else flatMapIndexed {i, c -> removeRange(i, i + 1).permute(str + c)}

val allDecoders = "abcdefg".permute().map { Decoder(it) }
class Decoder(master: String) {
    private val decode = HashMap<Char, Char>()
    private val intMap = mapOf(
        "abcefg" to 0,
        "cf" to 1,
        "acdeg" to 2,
        "acdfg" to 3,
        "bcdf" to 4,
        "abdfg" to 5,
        "abdefg" to 6,
        "acf" to 7,
        "abcdefg" to 8,
        "abcdfg" to 9
    )
    init {
        master.toCharArray().zip("abcdefg".toCharArray()).map { (key, default) ->  decode.put(key, default)}
    }
    fun decode(key: String): Int? {
        val str = key.toCharArray().map { decode[it] ?: error("Bad map")}
            .sorted()
            .joinToString("")

        return intMap[str]
    }
}
