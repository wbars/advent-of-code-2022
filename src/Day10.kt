fun main() {

    fun part1(input: List<String>): Int {
        var nextCycle = 20
        var curCycle = 0
        var x = 1
        var res = 0
        for (line in input) {
            if (line == "noop") {
                curCycle++
                if (curCycle == nextCycle) {
                    res += nextCycle * x
                    nextCycle += 40
                }


            } else {
                val v = line.split(" ")[1].toInt()
                x += v
                curCycle += 2
                if (curCycle >= nextCycle) {
                    res += nextCycle * (x - v)
                    nextCycle += 40
                }
            }
        }
        return res
    }

    fun part2(input: List<String>): Int {
        val a: MutableList<Int> = mutableListOf()
        var curCycle = 0
        var x = 1
        for (line in input) {
            a.add(x)
            if (line == "noop") {
                curCycle++
            } else {
                a.add(x)
                val v = line.split(" ")[1].toInt()
                x += v
                curCycle += 2
            }
        }

        for (i in 1 until 241) {
            if (((i - 1) % 40) in a[i - 1] - 1..a[i - 1] + 1) {
                print("#")
            } else {
                print(".")
            }
            if (i % 40 == 0) {
                println()
            }

        }
        return a.size
    }


    part1(readInput("input")).println()
    part2(readInput("input"))
}
