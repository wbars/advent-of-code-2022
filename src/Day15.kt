import java.lang.Integer.max
import java.lang.Integer.min
import kotlin.math.abs

fun main() {

    fun part1(input: List<String>): Int {
        val a = input.map {
            val m =
                Regex("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)").matchEntire(it)!!
            val x1 = m.groups[1]!!.value.toInt()
            val y1 = m.groups[2]!!.value.toInt()
            val x2 = m.groups[3]!!.value.toInt()
            val y2 = m.groups[4]!!.value.toInt()
            Pair(Pair(x1, y1), Pair(x2, y2))
        }

        val y = 2000000
        return (-5000000..5000000).count { c ->
            val any = a.any { p ->
                val distanceToBeacon = abs(p.first.first - p.second.first) + abs(p.first.second - p.second.second)
                val distanceToCandidate = abs(p.first.first - c) + abs(p.first.second - y)
                p.first != Pair(c, y) && p.second != Pair(c, y) && distanceToBeacon >= distanceToCandidate
            }
            any
        }
    }




    part1(readInput("input")).println()
}
