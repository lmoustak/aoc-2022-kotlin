import kotlin.math.absoluteValue
import kotlin.math.sign

data class RopeKnot(var x: Int = 0, var y: Int = 0)

fun main() {
    fun updatePlaces(head: RopeKnot, tail: RopeKnot) {
        val horizontalDistance = tail.x - head.x
        val verticalDistance = tail.y - head.y

        if (horizontalDistance.absoluteValue == 2 || verticalDistance.absoluteValue == 2) {
            tail.x -= horizontalDistance.sign
            tail.y -= verticalDistance.sign
        }
    }

    fun part1(input: List<String>): Int {
        val head = RopeKnot()
        val tail = RopeKnot()
        val placesVisited = mutableSetOf(0 to 0)

        for (move in input) {
            val (direction, steps) = move.split(' ')
            for (i in 1..steps.toInt()) {
                when (direction) {
                    "L" -> head.x--
                    "R" -> head.x++
                    "U" -> head.y++
                    "D" -> head.y--
                }

                updatePlaces(head, tail)

                placesVisited += tail.x to tail.y
            }
        }

        return placesVisited.size
    }

    fun part2(input: List<String>): Int {
        val knots = List(10) { _ -> RopeKnot() }
        val placesVisited = mutableSetOf(0 to 0)

        for (move in input) {
            val (direction, steps) = move.split(' ')
            for (i in 1..steps.toInt()) {
                val head = knots.first()

                when (direction) {
                    "L" -> head.x--
                    "R" -> head.x++
                    "U" -> head.y++
                    "D" -> head.y--
                }

                for (j in 0 until knots.size - 1) updatePlaces(knots[j], knots[j + 1])

                val tail = knots.last()
                placesVisited += tail.x to tail.y
            }
        }

        return placesVisited.size
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
