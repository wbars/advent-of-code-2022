fun main() {

    fun part1(input: List<String>): Int {
        val m = input[0].length
        val n = input.size
        val a: Array<BooleanArray> = Array(n) { BooleanArray(m) }

        fun checkMax(input: List<String>, i: Int, j: Int, max: Char): Char {
            val c = input[i][j]
            if (c > max) {
                a[i][j] = true
            }
            return maxOf(c, max)
        }


        for (j in 1 until m - 1) {
            var max = input[0][j]
            for (i in 1 until n - 1) {
                max = checkMax(input, i, j, max)
            }
        }

        for (j in 1 until m - 1) {
            var max = input[n - 1][j]
            for (i in n - 2 downTo  1) {
                max = checkMax(input, i, j, max)
            }
        }

        for (i in 1 until n - 1) {
            var max = input[i][0]
            for (j in 1 until m - 1) {
                max = checkMax(input, i, j, max)
            }
        }

        for (i in 1 until n - 1) {
            var max = input[i][m - 1]
            for (j in m - 2 downTo  1) {
                max = checkMax(input, i, j, max)
            }
        }

        var res = n * 2 + m * 2 - 4
        for (row in a) {
            res += row.count { it }
        }
        return res
    }




    part1(readInput("input")).println()
}
