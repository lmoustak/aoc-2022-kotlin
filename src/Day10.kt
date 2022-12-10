fun main() {
    val addPattern = Regex("""addx (-?\d+)""")

    fun part1(input: List<String>): Int {
        val targetCycles = mutableListOf(20, 60, 100, 140, 180, 220)
        var registerTotal = 0
        var register = 1
        var cycle = 1

        for (line in input) {
            if (cycle > targetCycles[0]) targetCycles.removeAt(0)
            if (targetCycles.isEmpty()) return registerTotal
            val target = targetCycles[0]

            var value = 0
            if (addPattern.matches(line)) {
                value = addPattern.find(line)!!.groupValues[1].toInt()
                cycle++
            }

            if (cycle >= target) {
                registerTotal += register * target
            }

            cycle++
            register += value
        }

        return registerTotal
    }

    fun part2(input: List<String>): String {
        var cycle = 0
        var register = 1
        val screen = Array(6) { Array(40) { '.' } }

        for (line in input) {
            if (cycle < 240) {
                var move = 0

                do {
                    val x = cycle % 40
                    val y = cycle / 40
                    if (x in register - 1..register + 1) screen[y][x] = '#'

                    register += move
                    move = if (move == 0) {
                        addPattern.find(line)?.groupValues?.get(1)?.toInt() ?: 0
                    } else {
                        0
                    }

                    cycle++
                } while (move != 0)
            }
        }

        return screen.joinToString("\n") { it.joinToString("") }
    }

    val input = readInput("Day10")
    println(part1(input))
    println(part2(input))
}
