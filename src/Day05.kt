import java.util.*

fun main() {
    val labelRegex = Regex("""\s+\d+""")
    val moveRegex = Regex("""move (\d+) from (\d+) to (\d+)""")

    fun part1(input: List<String>): String {
        val stacks = mutableListOf<Stack<Char>>()

        for (i in input.indices) {
            val line = input[i]
            val numberOfStacks = labelRegex.findAll(line).count()

            if (numberOfStacks > 0 && stacks.isEmpty()) {
                for (j in 1..numberOfStacks) stacks.add(Stack<Char>())

                for (j in i - 1 downTo 0) {
                    val row = input[j]
                    for (k in row.indices step 4) {
                        val crate = row[k + 1]
                        if (crate in 'A'..'Z') {
                            stacks[k / 4].push(crate)
                        }
                    }
                }

            } else if (moveRegex.matches(line)) {
                val groups = moveRegex.find(line)!!.groups
                val cratesToMove = groups[1]!!.value.toInt()
                val fromStack = groups[2]!!.value.toInt()
                val toStack = groups[3]!!.value.toInt()

                for (i in 1..cratesToMove) {
                    stacks[toStack - 1].push(stacks[fromStack - 1].pop())
                }
            }
        }

        return stacks.map { it.last() }.joinToString(separator = "")
    }

    fun part2(input: List<String>): String {
        val stacks = mutableListOf<Stack<Char>>()

        for (i in input.indices) {
            val line = input[i]
            val numberOfStacks = labelRegex.findAll(line).count()

            if (numberOfStacks > 0 && stacks.isEmpty()) {
                for (j in 1..numberOfStacks) stacks.add(Stack<Char>())

                for (j in i - 1 downTo 0) {
                    val row = input[j]
                    for (k in row.indices step 4) {
                        val crate = row[k + 1]
                        if (crate in 'A'..'Z') {
                            stacks[k / 4].push(crate)
                        }
                    }
                }

            } else if (moveRegex.matches(line)) {
                val groups = moveRegex.find(line)!!.groups
                val cratesToMove = groups[1]!!.value.toInt()
                val fromStack = groups[2]!!.value.toInt()
                val toStack = groups[3]!!.value.toInt()

                val tempStack = Stack<Char>()
                for (i in 1..cratesToMove) {
                    tempStack.push(stacks[fromStack - 1].pop())
                }

                for (i in tempStack.indices) {
                    stacks[toStack - 1].push(tempStack.pop())
                }
            }
        }

        return stacks.map { it.last() }.joinToString(separator = "")
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
