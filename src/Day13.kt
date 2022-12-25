import java.lang.Integer.min

sealed interface Value {
    fun lessThan(value: Value): Boolean?
}

class IntValue(val v: Int) : Value {
    override fun lessThan(value: Value): Boolean? {
        return if (value is IntValue) {
            if (this.v < value.v) true else if (this.v > value.v) false else null
        } else {
            ListValue(mutableListOf(this)).lessThan(value)
        }
    }

    override fun toString(): String {
        return v.toString()
    }
}

class ListValue(val values: MutableList<Value>) : Value {
    override fun lessThan(value: Value): Boolean? {
        if (value is IntValue) {
            return lessThan(ListValue(mutableListOf(value)))
        }
        require(value is ListValue)
        for (i in 0 until min(values.size, value.values.size)) {
            val res = values[i].lessThan(value.values[i])
            if (res != null) {
                return res
            }
        }
        if (values.size < value.values.size) {
            return true
        }
        else if (values.size > value.values.size) {
            return false
        }

        return null
    }

    override fun toString(): String {
        return values.joinToString(",")
    }
}

fun main() {

    fun parseValue(it: String): Value {
        val res = ListValue(mutableListOf())
        val a: ArrayDeque<ListValue> = ArrayDeque(listOf(res))
        var i = 1
        while (i < it.length - 1) {
            if (it[i] == '[') {
                a.addFirst(ListValue(mutableListOf()))
            } else if (it[i] == ']') {
                val element = a.removeFirst()
                a.first().values.add(element)
            }
            else if (it[i] == ',') {
                i++
                continue
            }
            else {
                var s = ""
                while (Character.isDigit(it[i])) s += it[i++]
                a.first().values.add(ListValue(mutableListOf(IntValue(s.toInt()))))
                continue
            }
            i++
        }
        return res
    }

    fun part1(input: List<String>): Int {
        return input.asSequence().filter { it.isNotEmpty() }.chunked(2)
            .map { it.map(::parseValue) }
            .withIndex()
            .filter { it.value[0].lessThan(it.value[1]) == true }
            .sumOf { it.index + 1 }

    }

    fun part2(input: List<String>): Int {
        val a = parseValue("[[2]]")
        val b = parseValue("[[6]]")
        val map = (input.filter { it.isNotEmpty() }.map { parseValue(it) } + listOf(a, b))
            .sortedWith { f, s -> if (f.lessThan(s) == true) -1 else if (s.lessThan(f) == true) 1 else 0 }
        return (map.indexOf(a) + 1) * (map.indexOf(b) + 1)

    }



    part1(readInput("input")).println()
    part2(readInput("input")).println()
}
