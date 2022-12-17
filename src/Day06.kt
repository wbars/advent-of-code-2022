

private fun getStacks() = listOf(
        ArrayDeque(listOf('R', 'Q', 'G', 'P', 'C', 'F')),
        ArrayDeque(listOf('P', 'C', 'T', 'W')),
        ArrayDeque(listOf('C', 'M', 'P', 'H', 'B')),
        ArrayDeque(listOf('R', 'P', 'M', 'S', 'Q', 'T', 'L')),
        ArrayDeque(listOf('N', 'G', 'V', 'Z', 'J', 'H', 'P')),
        ArrayDeque(listOf('J', 'P', 'D')),
        ArrayDeque(listOf('R', 'T', 'J', 'F', 'Z', 'P', 'G', 'L')),
        ArrayDeque(listOf('J', 'T', 'P', 'F', 'C', 'H', 'L', 'N')),
        ArrayDeque(listOf('W', 'C', 'T', 'H', 'Q', 'Z', 'V', 'G')),
)

fun main() {
    fun part1(input: List<String>): Int {
        val line = input[0]
        return (line.indices).find { line.substring(it, it + 4).toCharArray().toSet().size == 4 }!! + 4
    }




    part1(readInput("input")).println()
}
