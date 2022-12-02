enum class Roshambo(val points: Int) {
    ROCK(1), PAPER(2), SCISSORS(3)
}

val winningMap = mapOf(
    Roshambo.ROCK to Roshambo.PAPER,
    Roshambo.PAPER to Roshambo.SCISSORS,
    Roshambo.SCISSORS to Roshambo.ROCK
)

fun main() {
    fun part1(input: List<String>): Int {
        val roshamboMap = mapOf(
            "A" to Roshambo.ROCK,
            "B" to Roshambo.PAPER,
            "C" to Roshambo.SCISSORS,
            "X" to Roshambo.ROCK,
            "Y" to Roshambo.PAPER,
            "Z" to Roshambo.SCISSORS
        )

        var score = 0

        input.forEach {
            val picks = it.split(' ')
            val opponentPick = roshamboMap[picks[0]]!!
            val playerPick = roshamboMap[picks[1]]!!

            score += playerPick.points + if (opponentPick == playerPick) {
                3
            } else if (playerPick == winningMap[opponentPick]) {
                6
            } else {
                0
            }
        }

        return score
    }

    fun part2(input: List<String>): Int {
        val roshamboMap = mapOf(
            "A" to Roshambo.ROCK,
            "B" to Roshambo.PAPER,
            "C" to Roshambo.SCISSORS
        )

        var score = 0

        input.forEach {
            val picks = it.split(' ')
            val opponentPick = roshamboMap[picks[0]]!!
            val playerPick = when (picks[1]) {
                "Y" -> opponentPick
                "Z" -> winningMap[opponentPick]
                "X" -> winningMap.filterValues { pick -> pick == opponentPick }.keys.first()
                else -> throw IllegalArgumentException()
            }

            score += playerPick!!.points + if (opponentPick == playerPick) {
                3
            } else if (playerPick == winningMap[opponentPick]) {
                6
            } else {
                0
            }
        }

        return score
    }


    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
