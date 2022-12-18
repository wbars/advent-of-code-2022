fun main() {

    fun part1(input: List<String>): Int {
        var x = 0
        var y = 0
        var tx = 0
        var ty = 0
        val v: MutableSet<Pair<Int, Int>> = mutableSetOf()
        v.add(Pair(0, 0))
        for (s in input) {
            val parts = s.split(" ")
            repeat(parts[1].toInt()) {
                when (parts[0]) {
                    "R" -> {
                        x += 1
                        if (x - tx > 1) {
                            tx = x - 1
                            ty = y
                        }
                    }

                    "L" -> {
                        x -= 1
                        if (tx - x > 1) {
                            tx = x + 1
                            ty = y
                        }
                    }

                    "U" -> {
                        y += 1
                        if (y - ty > 1) {
                            ty = y - 1
                            tx = x
                        }
                    }

                    else -> {
                        y -= 1
                        if (ty - y > 1) {
                            ty = y + 1
                            tx = x
                        }
                    }
                }
                v.add(Pair(tx, ty))
            }
        }
        return v.size
    }






    part1(readInput("input")).println()
}
