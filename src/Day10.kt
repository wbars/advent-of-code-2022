import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        var nextCycle = 20
        var curCycle = 0
        var x = 1
        var res = 0
        for (line in input) {
            if (line == "noop") {
                curCycle++
                if (curCycle == nextCycle) {
                    res += nextCycle * x
                    nextCycle += 40
                }


            } else {
                val v = line.split(" ")[1].toInt()
                x += v
                curCycle += 2
                if (curCycle >= nextCycle) {
                    res += nextCycle * (x - v)
                    nextCycle += 40
                }
            }
        }
        return res
    }

    part1(readInput("input")).println()
}
