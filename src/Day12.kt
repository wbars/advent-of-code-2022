import kotlin.math.min

fun main() {
    fun part1(input: List<String>): Int {
        var iStart = 0
        var jStart = 0

        var iEnd = 0
        var jEnd = 0

        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == 'S') {
                    iStart = i
                    jStart = j
                } else if (input[i][j] == 'E') {
                    iEnd = i
                    jEnd = j
                }
            }
        }

        val a = Array(input.size) { IntArray(input[0].length) { Int.MAX_VALUE } }
        a[iStart][jStart] = 0
        val v = Array(input.size) { BooleanArray(input[0].length) { false } }
        val prev = Array(input.size) { Array(input[0].length) { "." } }

        while (true) {
            val pp = a.findMin(v)
            if (pp == null) {
                break
            }
            val i = pp.first
            val j = pp.second
            v[i][j] = true
            for ((ii, jj) in sequenceOf(Pair(i - 1, j), Pair(i + 1, j), Pair(i, j - 1), Pair(i, j + 1))) {
                if (ii in a.indices && jj in a[ii].indices && !v[ii][jj]) {
                    if (input.getSafe(i, j) - input.getSafe(ii, jj) >= -1) {
                        if (a[i][j] + 1 < a[ii][jj]) {
                            a[ii][jj] = min(a[ii][jj], a[i][j] + 1)
                            if (ii == i - 1) {
                                prev[i][j] = "↑"
                            } else if (ii == i + 1) {
                                prev[i][j] = "↓"
                            }
                            else if (jj == j - 1) {
                                prev[i][j] = "←"
                            }
                            else {
                                prev[i][j] = "→"
                            }

                        }

                    }
                }
            }
            if (i == iEnd && j == jEnd) {
                break
            }
        }
        println(prev.joinToString("\n", transform = {it.joinToString("")}))
        return a[iEnd][jEnd]
    }

    part1(readInput("input")).println()
}

private fun List<String>.getSafe(i: Int, j: Int): Char {
    return when (this[i][j]) {
        'S' -> 'a'
        'E' -> 'z'
        else -> this[i][j]
    }
}

fun Array<IntArray>.findMin(v: Array<BooleanArray>): Pair<Int, Int>? {
    var min = Int.MAX_VALUE
    var iMin = -1
    var jMin = -1
    for (i in indices) {
        for (j in this[i].indices) {
            if (!v[i][j] && min > this[i][j]) {
                min = this[i][j]
                iMin = i
                jMin = j
            }
        }
    }
    return if (iMin >= 0) Pair(iMin, jMin) else null
}
