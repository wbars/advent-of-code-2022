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

    part1(readInput("input")).println()
}
