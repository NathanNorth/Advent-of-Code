package tech.nathann.aoc.day14

import tech.nathann.aoc.Input
import java.math.BigInteger

var inputData = Input.getInputLinesWeb(14)

//3700829748840
fun main() {
    val rules = HashMap<String, String>()
    var pairs = mutableMapOf<String, BigInteger>()
    val count = mutableMapOf<String, BigInteger>()

    inputData.subList(2, inputData.size)
        .map {
            val sides = it.split(" -> ")
            rules.put(sides[0], sides[1])
        }

//    rules.keys.map {
//        println("Building cache for key $it")
//        var cycled = it
//        for(i in 0..10) {
//            cycled = doCycle(rules, cycled)
//        }
//        cache.put(it, cycled)
//    }

    var returnable = inputData[0]

//    for(i in 0 until 40) {
//        println("Step $i")
//        returnable = doCycle(rules, returnable)
//    }

    inputData[0].zipWithNext()
        .map {
            (l, r) -> pairs.incr("$l$r", BigInteger.ONE)
        }
    inputData[0].map { count.incr(it.toString(), BigInteger.ONE) }

    for(i in 0 until 40) {
        println("Step $i")
        val keys = pairs.keys.toList()
        val newPairs = mutableMapOf<String, BigInteger>()
        keys.forEach { str ->
                val num = pairs[str]!!
                val l = str[0]
                val r = str[1]
                val middle = rules[str]!!
                count.incr(middle, num)
                newPairs.incr("$l$middle", num)
                newPairs.incr("$middle$r", num)
            }
        pairs = newPairs
    }

//    val ints = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".map { let -> returnable.count { it == let } }
//        .sortedDescending()
//        .filter { it != 0 }
    val ints = count.values.sortedDescending()

    val result = ints[0].subtract(ints[ints.size - 1])

    println(result)
}
fun MutableMap<String, BigInteger>.incr(key: String, num: BigInteger) {
    this[key] = this.getOrDefault(key, BigInteger.ZERO).add(num)
}
fun MutableMap<String, BigInteger>.decr(key: String, num: BigInteger) {
    this[key] = this.getOrDefault(key, BigInteger.ZERO).add(num)
}
fun doCycle(rules: Map<String, String>, input: String): String = input
    .zipWithNext()
    .map { (prev, next) -> rules["$prev$next"]!!.substring(1) }
    .fold(inputData[0][0] + "") { str, next -> str + next}