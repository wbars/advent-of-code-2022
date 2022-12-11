data class Range(val l: Int, var r: Int) {
    fun contains(range: Range): Boolean {
        return l <= range.l && r >= range.r
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return input.map {
            val i = it.indexOf(',')
            Pair(it.substring(0, i).range(), it.substring(i + 1).range())
        }.count { it.first.contains(it.second) || it.second.contains(it.first) }
    }

    part1(readInput("input")).println()
}

private fun String.range(): Range {
    val split = split("-")
    return Range(split[0].toInt(), split[1].toInt())
}
