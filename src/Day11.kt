import java.util.*

enum class Operation(val value: String) { ADDITION("+"), MULTIPLICATION("*") }

data class Monkey(
    val items: MutableList<Long>,
    val operation: Operation,
    val value: Int?,
    val divisibleBy: Int,
    val targetIfTrue: Int,
    val targetIfFalse: Int,
    var inspections: Long = 0L
)

fun main() {
    val startingItemsPattern = Regex("""\s*Starting items: ((\d+(,\s*)?)+)""")
    val operationPattern = Regex("""\s*Operation: new = old ([+\-*/]) (\d+|old)""")
    val testAndTargetPattern = Regex(""".+ (\d+)""")

    fun part1(input: List<String>): Long {
        val monkeys = mutableListOf<Monkey>()

        val sanitizedInput = input.filter { it.isNotBlank() }

        for (i in sanitizedInput.indices step 6) {
            val items =
                startingItemsPattern.find(sanitizedInput[i + 1])!!.groupValues[1].split(", ").map { it.toLong() }
                    .toMutableList()

            val (_, operationString, valueString) = operationPattern.find(sanitizedInput[i + 2])!!.groupValues
            val operation = Operation.values().find { it.value == operationString }!!
            val value = if (valueString == "old") {
                null
            } else {
                valueString.toInt()
            }

            val divisibleBy = testAndTargetPattern.find(sanitizedInput[i + 3])!!.groupValues[1].toInt()
            val targetIfTrue = testAndTargetPattern.find(sanitizedInput[i + 4])!!.groupValues[1].toInt()
            val targetIfFalse = testAndTargetPattern.find(sanitizedInput[i + 5])!!.groupValues[1].toInt()

            monkeys.add(Monkey(items, operation, value, divisibleBy, targetIfTrue, targetIfFalse))
        }

        for (i in 1..20) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    val value = monkey.value?.toLong() ?: item

                    val newItem = when (monkey.operation) {
                        Operation.ADDITION -> item + value
                        Operation.MULTIPLICATION -> item * value
                    } / 3L

                    val nextMonkey = monkeys[if (newItem % monkey.divisibleBy.toLong() == 0L) {
                        monkey.targetIfTrue
                    } else {
                        monkey.targetIfFalse
                    }]

                    nextMonkey.items += newItem
                    monkey.inspections++
                }

                monkey.items.clear()
            }
        }

        monkeys.sortByDescending { it.inspections }

        return monkeys.take(2).map { it.inspections }.reduce(Long::times)
    }

    fun part2(input: List<String>): Long {
        val monkeys = mutableListOf<Monkey>()

        val sanitizedInput = input.filter { it.isNotBlank() }

        for (i in sanitizedInput.indices step 6) {
            val items =
                startingItemsPattern.find(sanitizedInput[i + 1])!!.groupValues[1].split(", ").map { it.toLong() }
                    .toMutableList()

            val (_, operationString, valueString) = operationPattern.find(sanitizedInput[i + 2])!!.groupValues
            val operation = Operation.values().find { it.value == operationString }!!
            val value = if (valueString == "old") {
                null
            } else {
                valueString.toInt()
            }

            val divisibleBy = testAndTargetPattern.find(sanitizedInput[i + 3])!!.groupValues[1].toInt()
            val targetIfTrue = testAndTargetPattern.find(sanitizedInput[i + 4])!!.groupValues[1].toInt()
            val targetIfFalse = testAndTargetPattern.find(sanitizedInput[i + 5])!!.groupValues[1].toInt()

            monkeys.add(Monkey(items, operation, value, divisibleBy, targetIfTrue, targetIfFalse))
        }

        val mod = monkeys.map { it.divisibleBy.toLong() }.reduce(Long::times)

        for (i in 1..10_000) {
            for (monkey in monkeys) {
                for (item in monkey.items) {
                    val value = monkey.value?.toLong() ?: item

                    val newItem = when (monkey.operation) {
                        Operation.ADDITION -> item + value
                        Operation.MULTIPLICATION -> item * value
                    } % mod

                    val nextMonkey = monkeys[if (newItem % monkey.divisibleBy == 0L) {
                        monkey.targetIfTrue
                    } else {
                        monkey.targetIfFalse
                    }]

                    nextMonkey.items += newItem
                    monkey.inspections++
                }

                monkey.items.clear()
            }
        }

        monkeys.sortByDescending { it.inspections }

        return monkeys.take(2).map { it.inspections }.reduce(Long::times)
    }

    val input = readInput("Day11")
    println(part1(input))
    println(part2(input))
}
