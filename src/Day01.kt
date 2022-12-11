import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var acc = 0
        var max = 0
        for (s in input) {
            if (s.isEmpty()) {
                max = max(max, acc)
                acc = 0
            } else {
                acc += s.toInt()
            }
        }
        return max(max, acc)
    }

    fun part2(input: List<String>): Int {
        var acc = 0
        val all = mutableListOf<Int>()
        for (s in input) {
            if (s.isEmpty()) {
                all.add(acc)
                acc = 0
            } else {
                acc += s.toInt()
            }
        }
        all.add(acc)
        return all.sortedDescending().take(3).sum()
    }


    part1(readInput("input")).println()
    part2(readInput("input")).println()
}
