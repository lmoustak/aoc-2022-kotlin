import kotlin.math.max

fun main() {
    fun part1(input: List<String>): Int {
        var currentCalories = 0
        var maxCalories = 0

        input.forEach {
            if (it.isBlank()) {
                maxCalories = max(maxCalories, currentCalories)
                currentCalories = 0
            } else {
                currentCalories += it.toInt()
            }
        }

        maxCalories = max(maxCalories, currentCalories)

        return maxCalories
    }

    fun part2(input: List<String>): Int {
        var currentCalories = 0
        val elves = mutableListOf<Int>()

        input.forEach {
            if (it.isBlank()) {
                elves.add(currentCalories)
                currentCalories = 0
            } else {
                currentCalories += it.toInt()
            }
        }

        elves.sortDescending()

        return elves[0] + elves[1] + elves[2]
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
