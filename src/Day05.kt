

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
    fun part1(input: List<String>): String {
        val stacks = getStacks()
        val regex = Regex("move (\\d+) from (\\d+) to (\\d+)")
        for (line in input) {
            val res = regex.matchEntire(line)!!
            val count = res.groups[1]!!.value.toInt()
            val from = res.groups[2]!!.value.toInt()
            val to = res.groups[3]!!.value.toInt()

            for (i in 0 until count) {
                stacks[to - 1].addFirst(stacks[from-1].removeFirst())
            }
        }
        return stacks.map { it.first() }.joinToString("")
    }

    fun part2(input: List<String>): String {
        val stacks = getStacks()
        val regex = Regex("move (\\d+) from (\\d+) to (\\d+)")
        for (line in input) {
            val res = regex.matchEntire(line)!!
            val count = res.groups[1]!!.value.toInt()
            val from = res.groups[2]!!.value.toInt()
            val to = res.groups[3]!!.value.toInt()

            val tmp = ArrayDeque<Char>(count)
            for (i in 0 until count) {
                tmp.addFirst(stacks[from-1].removeFirst())
            }
            for (c in tmp) {
                stacks[to-1].addFirst(c)
            }
        }
        return stacks.map { it.first() }.joinToString("")
    }




    part1(readInput("input")).println()
    part2(readInput("input")).println()
}
