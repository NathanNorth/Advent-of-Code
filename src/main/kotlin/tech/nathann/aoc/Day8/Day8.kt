package tech.nathann.aoc.Day8

import tech.nathann.aoc.Input

val inputData = Input.getInputLinesWeb(8)

fun main() {
    val s = "gdshlf".toCharArray()


    println(doTheThing())
    part2()
}
fun doTheThing(): Int {
    val flatList = inputData.toList().map { it.split("|")[1].split(" ") }.flatten()
    return flatList.count {
        it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7
    }
}
fun part2() {
    val data = inputData.map { it.split("|")[0].split(" ")
        .map {
            val arr = it.toCharArray()
            //arr.sort()
            String(arr)
        }
    }
    val output = inputData.map { it.split("|")[1].split(" ")
        .map {
            val arr = it.toCharArray()
            //arr.sort()
            String(arr)
        }
    }

    data.zip(output).map { (data, output) -> Line(data, output) }
        .map { println(it.map) }
}
data class Line(val data: List<String>, val output: List<String>) {
    val map = HashMap<Int, String>()
    init {
        println(data)
        println(output)
        for(s in output) {
            println(s)
            val num = when(s.length)
            {
                2 -> 1
                3 -> 7
                4 -> 4
                7 -> 8
                else -> -1
            }
            if(num != -1)
                map[num] = s
        }
    }
}
class Decoder(val master: String) {
    val decode = HashMap<Char, Char>()
    init {
        master.toCharArray().zip("abcdefg".toCharArray()).map { (key, default) ->  decode.put(key, default)}
    }

}