import java.lang.IllegalStateException
import kotlin.math.abs

private const val i = 4000000

fun main() {

    fun beacons(input: List<String>): List<Pair<Pair<Int, Int>, Pair<Int, Int>>> {
        val a = input.map {
            val m =
                Regex("Sensor at x=(-?\\d+), y=(-?\\d+): closest beacon is at x=(-?\\d+), y=(-?\\d+)").matchEntire(it)!!
            val x1 = m.groups[1]!!.value.toInt()
            val y1 = m.groups[2]!!.value.toInt()
            val x2 = m.groups[3]!!.value.toInt()
            val y2 = m.groups[4]!!.value.toInt()
            Pair(Pair(x1, y1), Pair(x2, y2))
        }
        return a
    }

    fun getDistanceToBeacon(p: Pair<Pair<Int, Int>, Pair<Int, Int>>, x: Int, y: Int) =
        abs(p.first.first - x) + abs(p.first.second - y)

    fun getDistanceToBeacon(p: Pair<Pair<Int, Int>, Pair<Int, Int>>) =
        getDistanceToBeacon(p, p.second.first, p.second.second)

    fun part1(input: List<String>): Int {
        val a = beacons(input)

        val y = 2000000
        return (-5000000..5000000).count { c ->
            val any = a.any { p ->
                val distanceToBeacon = getDistanceToBeacon(p)
                val distanceToCandidate = abs(p.first.first - c) + abs(p.first.second - y)
                p.first != Pair(c, y) && p.second != Pair(c, y) && distanceToBeacon >= distanceToCandidate
            }
            any
        }
    }

    fun part2(input: List<String>): Long {
        fun res(x: Int, y: Int): Long {
            return x.toLong() * 4000000L + y.toLong()
        }
        val top = 4000000
        fun canExistInAllBeacons(
            a: List<Pair<Pair<Int, Int>, Pair<Int, Int>>>,
            x: Int,
            y: Int
        ) = a.all { p ->
            if (!(x in 0 .. top && y in 0 .. top)) false
            else {
                val distanceToBeacon = getDistanceToBeacon(p)
                val distanceToCandidate = abs(p.first.first - x) + abs(p.first.second - y)
                p.first != Pair(x, y) && p.second != Pair(x, y) && distanceToBeacon < distanceToCandidate
            }
        }

        val a = beacons(input)
        for (pair in a) {
            val distanceToBeacon = getDistanceToBeacon(pair)

            var y = pair.first.second
            for (x in pair.first.first - distanceToBeacon - 1..pair.first.first) {
                if (canExistInAllBeacons(a, x, y)) {
                    return res(x,y)
                }
                y--
            }

            for (x in pair.first.first..pair.first.first + distanceToBeacon + 1) {
                if (canExistInAllBeacons(a, x, y)) {
                    return res(x,y)
                }
                y++
            }

            for (x in pair.first.first + distanceToBeacon + 1 downTo pair.first.first) {
                if (canExistInAllBeacons(a, x, y)) {
                    return res(x,y)
                }
                y++
            }

            for (x in pair.first.first + distanceToBeacon + 1 downTo pair.first.first) {
                if (canExistInAllBeacons(a, x, y)) {
                    return res(x,y)
                }
                y--
            }
        }
        throw IllegalStateException()
    }

    part1(readInput("input")).println()
    part2(readInput("input")).println()
}
