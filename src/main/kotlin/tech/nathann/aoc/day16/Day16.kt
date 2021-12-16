package tech.nathann.aoc.day16

import tech.nathann.aoc.Binary
import tech.nathann.aoc.Input

val inputData = Input.getInputLinesWeb(16)

fun main() {
    var binary = Binary(inputData[0], hexidecimal = true)
    println(Packet(binary).number ?: -1)
}
class Packet(binary: Binary) {
    private val version = binary.subBinary(0, 3).toDecimalLong()
    private val type = binary.subBinary(3, 6).toDecimalLong()
    var number: Long? = null //todo var cringe
    private val containing = mutableListOf<Packet>() //todo var cringe
    var leftOver = Binary()
    init {
        if(type == 4L) { //string literal
            var data = binary.subBinary(6)
            var num = Binary()
            while(true) {
                num.append(data.subBinary(1, 5))
                if(data.subBinary(0, 1).toBinaryLong() == 0L) break
                data = data.subBinary(5)
            }
            number = num.toDecimalLong()
            leftOver = data.subBinary(0, 5)
            println("Leftover is \"${data.subBinary(5)}\"")
        }
        else { //something with subpackets
            val lengthTypeId = binary.subBinary(6, 7).toBinaryLong()
            var data = binary.subBinary(7)
            if(lengthTypeId == 0L) {
                val totalLengthInBits = data.subBinary(0, 15).toDecimalLong()

            }
        }
    }


    fun versionSums(): Long = version + containing.sumOf { it.versionSums() }

}