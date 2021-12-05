import tech.nathann.aoc.Input

val inputData = Input.getInputLinesWeb(5)

//part 2 sol
fun main() {
    var sums = Array(1000) {IntArray(1000)}
    val rows = inputData.map { Line(it) }
        .filter {it.y1 == it.y2 }
    val cols  = inputData.map { Line(it) }
        .filter {it.x1 == it.x2 }
    val diags  = inputData.map { Line(it) }
        .filter {it.x1 != it.x2 && it.y1 != it.y2 }

    for(line in rows) {
        for(x in Math.min(line.x1, line.x2) .. Math.max(line.x1, line.x2)) {
            sums[x][line.y1] += 1
        }
    }
    for(line in cols) {
        for(y in Math.min(line.y1, line.y2) .. Math.max(line.y1, line.y2)) {
            sums[line.x1][y] += 1
        }
    }
    for(diag in diags) {
        var xs: IntProgression = diag.x1..diag.x2
        var ys: IntProgression = diag.y1..diag.y2

        if(xs.isEmpty()) xs = diag.x1 downTo diag.x2
        if(ys.isEmpty()) ys = diag.y1 downTo diag.y2

        for(i in 0 until xs.toList().size) {
            sums[xs.toList()[i]][ys.toList()[i]]++
        }
    }

    var returnable = 0
    for(r in 0 until 1000) {
        for(c in 0 until 1000) {
            if(sums[r][c] >= 2) returnable++
        }
    }
    println(returnable)
}

//actually part 1 solution lmao
fun main2() {
    var sums = Array(1000) {IntArray(1000)}
    val rows = inputData.map { Line(it) }
        .filter {it.y1 == it.y2 }
    val cols  = inputData.map { Line(it) }
        .filter {it.x1 == it.x2 }

    for(line in rows) {
        for(x in Math.min(line.x1, line.x2) .. Math.max(line.x1, line.x2)) {
            sums[x][line.y1] += 1
        }
    }
    for(line in cols) {
        for(y in Math.min(line.y1, line.y2) .. Math.max(line.y1, line.y2)) {
            sums[line.x1][y] += 1
        }
    }

    var returnable = 0
    for(r in 0 until 1000) {
        for(c in 0 until 1000) {
            if(sums[r][c] >= 2) returnable++
        }
    }
    println(returnable)
}
class Line(val input: String) {
    private val nums = input.split(Regex(",|( -> )"))
    val x1 = nums[0].toInt()
    val y1 = nums[1].toInt()
    val x2 = nums[2].toInt()
    val y2 = nums[3].toInt()
    override fun toString(): String {
        return "Line(input='$input', nums=$nums, x1=$x1, y1=$y1, x2=$x2, y2=$y2)"
    }
}