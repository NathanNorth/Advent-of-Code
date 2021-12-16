package tech.nathann.aoc


private val hexTable = mapOf(
    '0' to "0000",
    '1' to "0001",
    '2' to "0010",
    '3' to "0011",
    '4' to "0100",
    '5' to "0101",
    '6' to "0110",
    '7' to "0111",
    '8' to "1000",
    '9' to "1001",
    'A' to "1010",
    'B' to "1011",
    'C' to "1100",
    'D' to "1101",
    'E' to "1110",
    'F' to "1111"
)
class Binary(string: String = "", hexidecimal: Boolean = false) {
    private var stringRepresentation: String = ""

    init {
        if(!hexidecimal) {
            stringRepresentation = string;
        }
        else {
            stringRepresentation = string
                .map { hexTable[it] }
                .fold("") {acc, s ->  acc + s}
        }
    }
    fun append(other: Binary): Binary {
        stringRepresentation += other
        return this
    }
    fun subBinary(from: Int, to: Int = stringRepresentation.length) = Binary(stringRepresentation.substring(from, to))
    fun size(): Int = stringRepresentation.length
    fun toBinaryLong(): Long = stringRepresentation.toLong()
    fun toDecimalLong(): Long = stringRepresentation.toLong(radix = 2)
    override fun toString(): String {
        return stringRepresentation
    }

}