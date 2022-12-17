fun main() {
    fun solve(input: List<String>, size: Int): Int {
        val line = input[0]
        return (line.indices).find { line.substring(it, it + size).toCharArray().toSet().size == size }!! + size
    }

    fun part1(input: List<String>): Int {
        return solve(input, 4)
    }

    fun part2(input: List<String>): Int {
        return solve(input, 14)
    }

    part1(readInput("input")).println()
    part2(readInput("input")).println()
}
