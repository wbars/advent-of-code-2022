val alphabet = (('a'..'z') + ('A' .. 'Z')).toList()
fun main() {
    fun part1(input: List<String>): Int {
        return input.asSequence()
                .map {s ->
                    val i = (0 until s.length / 2).find { s.indexOf(s[it], s.length / 2) >= 0 }!!
                    alphabet.indexOf(s[i]) + 1
                }
                .sum()
    }


    part1(readInput("input")).println()
}