package tech.nathann.aoc.day13

import tech.nathann.aoc.Input

var inputData = Input.getInputLinesWeb(13)

fun main() {
    val max = inputData.filter { it.contains(",") }.flatMap {
        it.split(",").map { it.toInt() }
    }.maxOrNull()!!

    var arr = Array(max + 1) {Array(max + 1) {0} }
    inputData.filter { it.contains(",") }.map {
        it.split(",").map { it.toInt() }
    } .map { (x, y) -> arr[y][x]++ }

    inputData.filter { it.contains("fold") }
        //.take(1)
        .map {

            for (i in arr.indices) {
                println(arr[i].toList().toString())
            }
            println()

            val num = it.split("=")[1].toInt()
            if(it.contains("x")) {
                return@map arr.foldX(num)

            }
            return@map arr.foldY(num)
        }

    for (i in arr.indices) {
        println(arr[i].toList().toString())
    }
    println()

    val arr2 = arr.map { it.map { if(it > 0) "#" else "." } }

    for (i in 0..10) {
        for(ii in 0..100) {
            print(arr2[i][ii])
        }
        println()
    }

    println(arr.sumOf { it.count { it > 0 } })
}

fun Array<Array<Int>>.foldY(num: Int) {
    for(y in num .. num * 2) {
        for(x in 0 until this[0].size) {
            this[num * 2 - y][x] += this[y][x]
            this[y][x] = 0
        }
    }
}

fun Array<Array<Int>>.foldX(num: Int) {
    for(x in num .. num * 2) {
        for(y in 0 until this.size) {
            this[y][num * 2 - x] += this[y][x]
            this[y][x] = 0
        }
    }
}
