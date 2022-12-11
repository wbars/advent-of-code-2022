fun main() {
    fun part1(input: List<String>): Int {
        return input.asSequence()
                .map { Pair(it[0].toCode(), it[2].toCode()) }
                .map { it.second + 1 + it.solve() }
                .sum()
    }

    fun part2(input: List<String>): Int {
        return input.asSequence()
                .map {
                    val first = it[0] - 'A'
                    Pair(first, it[2].toCode(first))
                }
                .map { it.second + 1 + it.solve() }
                .sum()
    }



    part1(readInput("input")).println()
    part2(readInput("input")).println()
}

private fun Char.toCode(p: Int): Int {
    return when (this) {
        'X' -> (p - 1).let { if (it < 0) 2 else it  }
        'Y' -> p
        else -> (p + 1) % 3
    }
}

private fun Char.toCode(): Int {
    return when (this) {
        'A', 'X' -> 0
        'B', 'Y' -> 1
        else -> 2
    }
}

private fun Pair<Int, Int>.solve(): Int {
    return if (first == second) {
        3
    } else if ((first + 1) % 3 == second) {
        6
    } else {
        0
    }

}
