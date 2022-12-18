import kotlin.math.abs

fun main() {

    fun newPos(x: Int, y: Int, tx: Int, ty: Int): Pair<Int, Int> {
        var tx1 = tx
        var ty1 = ty
        if (x > tx1 && abs(x - tx1) > 1) {
            tx1++
            if (y > ty1) ty1++ else if (y < ty1) ty1--
        } else if (tx1 > x && abs(tx1 - x) > 1) {
            tx1--
            if (y > ty1) ty1++ else if (y < ty1) ty1--
        } else if (y > ty1 && abs(y - ty1) > 1) {
            ty1++
            if (x > tx1) tx1++ else if (x < tx1) tx1--
        } else if (ty1 > y && abs(ty1 - y) > 1) {
            ty1--
            if (x > tx1) tx1++ else if (x < tx1) tx1--
        }
        return Pair(tx1, ty1)
    }

    fun part1(input: List<String>): Int {
        var x = 0
        var y = 0
        var pos: Pair<Int, Int> = Pair(0, 0)
        val v: MutableSet<Pair<Int, Int>> = mutableSetOf()
        for (s in input) {
            val parts = s.split(" ")
            repeat(parts[1].toInt()) {
                when (parts[0]) {
                    "R" -> {
                        x += 1
                    }

                    "L" -> {
                        x -= 1
                    }

                    "U" -> {
                        y += 1
                    }

                    else -> {
                        y -= 1
                    }
                }
                pos = newPos(x, y, pos.first, pos.second)
                v.add(pos)
            }
        }
        return v.size
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var y = 0
        val pos: MutableList<Pair<Int, Int>> = mutableListOf(
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
                Pair(0, 0),
        )
        val v: MutableSet<Pair<Int, Int>> = mutableSetOf()
        for (s in input) {
            val parts = s.split(" ")
            repeat(parts[1].toInt()) {
                when (parts[0]) {
                    "R" -> {
                        x += 1
                    }

                    "L" -> {
                        x -= 1
                    }

                    "U" -> {
                        y += 1
                    }

                    else -> {
                        y -= 1
                    }
                }

                for (i in 0 until pos.size) {
                    val prev = if (i == 0) Pair(x, y) else pos[i - 1]
                    pos[i] = newPos(prev.first, prev.second, pos[i].first, pos[i].second)
                }
                v.add(pos.last())
            }
        }
        return v.size
    }







    part1(readInput("input")).println()
    part2(readInput("input")).println()
}
