val alphabet = (('a'..'z') + ('A' .. 'Z')).toList()
fun main() {
    fun duplicates(first: String, second: String) =
            (first.indices)
                    .filter { second.indexOf(first[it]) >= 0 }
                    .map { first[it] }

    fun priority(element: Char) = alphabet.indexOf(element) + 1

    fun findDuplicate(first: String, second: String): Int {
        return priority(duplicates(first, second).first())
    }

    fun part1(input: List<String>): Int {
        return input.asSequence()
                .map { findDuplicate(it, it.substring(it.length / 2)) }
                .sum()
    }

    fun part2(input: List<String>): Int {
        return input.chunked(3).asSequence()
                .map { duplicates(it[0], it[1]).find { dupe -> it[2].indexOf(dupe) >= 0 }!! }
                .map { priority(it) }
                .sum()
    }



    part1(readInput("input")).println()
    part2(readInput("input")).println()
}