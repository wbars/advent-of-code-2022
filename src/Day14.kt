import java.lang.Integer.max
import java.lang.Integer.min

fun main() {

    fun part1(input: List<String>): Int {
        val a: Array<CharArray> = Array(1000) { CharArray(1000) { '.' } }
        input.asSequence().filter { it.isNotEmpty() }
            .map { it.split(" -> ") }
            .map {
                it.map { p ->
                    val pp = p.split(",")
                    Pair(pp[0].toInt(), pp[1].toInt())
                }
            }
            .forEach {
                var cur = it[0]
                a[cur.second][cur.first] = '#'
                for (i in 1 until it.size) {
                    for (ii in min(cur.second, it[i].second)..max(cur.second, it[i].second)) {
                        a[ii][cur.first] = '#'
                    }
                    for (jj in min(cur.first, it[i].first)..max(cur.first, it[i].first)) {
                        a[cur.second][jj] = '#'
                    }
                    cur = it[i]
                }
            }
        a[0][500] = '+'
        val deepest = a.indices.filter { a[it].contains('#') }.max()
        var res = 0
//        println(a.joinToString("\n", transform = {it.joinToString("")}))
        while (true) {
            var i = 0
            var j = 500
            while (i <= deepest) {
                if (a[i + 1][j] == '.') {
                    i++
                }
                else if (a[i + 1][j - 1] == '.') {
                    i++
                    j--
                }
                else if (a[i + 1][j + 1] == '.') {
                    i++
                    j++
                } else {
                    a[i][j] = 'o'
                    res++
                    break
                }
            }
            if (i > deepest) {
//                println(a.joinToString("\n", transform = {it.joinToString("")}))
                return res
            }
        }
    }

    fun part2(input: List<String>): Int {
        val a: Array<CharArray> = Array(1000) { CharArray(1000) { '.' } }
        input.asSequence().filter { it.isNotEmpty() }
            .map { it.split(" -> ") }
            .map {
                it.map { p ->
                    val pp = p.split(",")
                    Pair(pp[0].toInt(), pp[1].toInt())
                }
            }
            .forEach {
                var cur = it[0]
                a[cur.second][cur.first] = '#'
                for (i in 1 until it.size) {
                    for (ii in min(cur.second, it[i].second)..max(cur.second, it[i].second)) {
                        a[ii][cur.first] = '#'
                    }
                    for (jj in min(cur.first, it[i].first)..max(cur.first, it[i].first)) {
                        a[cur.second][jj] = '#'
                    }
                    cur = it[i]
                }
            }
        a[0][500] = '+'
        val deepest = a.indices.filter { a[it].contains('#') }.max()
        for (j in 0 until a[deepest + 2].size) {
            a[deepest + 2][j] = '#'
        }
        var res = 0
        println(a.joinToString("\n", transform = {it.joinToString("")}))
        while (true) {
            var i = 0
            var j = 500
            if (a[i][j] == 'o') {
                println(a.joinToString("\n", transform = {it.joinToString("")}))
                return res
            }
            while (true) {
                if (a[i + 1][j] == '.') {
                    i++
                }
                else if (a[i + 1][j - 1] == '.') {
                    i++
                    j--
                }
                else if (a[i + 1][j + 1] == '.') {
                    i++
                    j++
                } else {
                    a[i][j] = 'o'
                    res++
                    break
                }
            }
        }
    }




    part1(readInput("input")).println()
    part2(readInput("input")).println()
}
