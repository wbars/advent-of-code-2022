class Node(val name: String, val parent: Node? = null, val children: MutableMap<String, Node> = mutableMapOf(), val size: Long = 0) {
    override fun toString(): String {
        return name + " " + (if (children.isEmpty()) size else "dir")
    }
}
fun main() {
    fun putNode(cur: Node, dir: String, size: Long = 0) = cur.children.computeIfAbsent(dir) { Node(dir, cur, size = size) }

    fun buildTree(input: List<String>): Node {
        val root = Node("/")
        var cur = root
        var i = 0
        while (i < input.size) {
            val line = input[i++]
            val cd = "$ cd "
            if (line.startsWith(cd)) {
                val dir = line.substring(cd.length)
                cur = when (dir) {
                    ".." -> cur.parent!!
                    "/" -> root
                    else -> putNode(cur, dir)
                }
            } else if (line == "$ ls") {
                while (i < input.size && !input[i].startsWith("$")) {
                    val lsLine = input[i++]
                    val part = lsLine.split(" ")
                    putNode(cur, part[1], if (part[0] == "dir") 0 else part[0].toLong())
                }
            }
        }
        return root
    }

    fun dfs(root: Node, directorySizeCallback: (Long) -> Unit): Long {
        if (root.children.isEmpty()) {
            return root.size
        }
        val sum = root.children.values.sumOf { dfs(it, directorySizeCallback) }
        directorySizeCallback(sum)
        return sum
    }


    fun part1(input: List<String>): Long {
        val root = buildTree(input)
        var res = 0L
        dfs(root) {
            if (it <= 100000) {
                res += it
            }
        }
        return res
    }

    fun part2(input: List<String>): Long {
        val root = buildTree(input)
        val sizes: MutableList<Long> = mutableListOf()
        var rootSize = 0L
        dfs(root) {
            sizes.add(it)
            rootSize = it
        }
        sizes.sort()
        return sizes.find { 70000000 - rootSize + it >= 30000000 }!!
    }



    part1(readInput("input")).println()
    part2(readInput("input")).println()
}
