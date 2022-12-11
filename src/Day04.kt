import java.lang.Integer.min
import kotlin.math.max

data class Range(val l: Int, var r: Int) {
    fun contains(range: Range): Boolean {
        return l <= range.l && r >= range.r
    }

    fun overlap(second: Range): Boolean {
        return max(second.l, l) <= min(second.r, r)
    }
}

fun main() {
    fun ranges(input: List<String>) = input.map {
        val i = it.indexOf(',')
        Pair(it.substring(0, i).range(), it.substring(i + 1).range())
    }

    fun part1(input: List<String>): Int {
        return ranges(input).count { it.first.contains(it.second) || it.second.contains(it.first) }
    }

    fun part2(input: List<String>): Int {
        return ranges(input).count { it.first.overlap(it.second)  }
    }


    part1(readInput("input")).println()
    part2(readInput("input")).println()
}

private fun String.range(): Range {
    val split = split("-")
    return Range(split[0].toInt(), split[1].toInt())
}
