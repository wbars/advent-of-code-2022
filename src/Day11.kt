class Monkey(var items: ArrayDeque<Int>, val test: Int, val trueMonkey: Int, val falseMonkey: Int, val operation: (Int) -> Int)
fun main() {
    val monkeys = listOf(
            Monkey(ArrayDeque(listOf(53, 89, 62, 57, 74, 51, 83, 97)), 13, 1, 5) { it * 3 },
            Monkey(ArrayDeque(listOf(85, 94, 97, 92, 56)), 19, 5, 2) { it + 2 },
            Monkey(ArrayDeque(listOf(86, 82, 82)), 11, 3, 4) { it + 1 },
            Monkey(ArrayDeque(listOf(94, 68)), 17, 7, 6) { it + 5 },
            Monkey(ArrayDeque(listOf(83, 62, 74, 58, 96, 68, 85)), 3, 3, 6) { it + 4 },
            Monkey(ArrayDeque(listOf(50, 68, 95, 82)), 7, 2, 4) { it + 8 },
            Monkey(ArrayDeque(listOf(75)), 5, 7, 0) { it * 7 },
            Monkey(ArrayDeque(listOf(92, 52, 85, 89, 68, 82)), 2, 0, 1) { it * it },
    )
    fun part1(input: List<String>): Int {
        val count = IntArray(monkeys.size)
        repeat(20) {
            for ((index, monkey) in monkeys.withIndex()) {
                while (!monkey.items.isEmpty()) {
                    count[index]++
                    val v = monkey.items.removeFirst()
                    val vv = monkey.operation(v) / 3
                    if (vv % monkey.test == 0) {
                        monkeys[monkey.trueMonkey].items.addLast(vv)
                    } else {
                        monkeys[monkey.falseMonkey].items.addLast(vv)
                    }
                }
            }
        }
        val s = count.sortedDescending()
        return s[0] * s[1]
    }



    part1(readInput("input")).println()
}
