fun main() {
    fun stringToRange(s: String): IntRange {
        val endpoints = s.split('-')
        val start = endpoints[0].toInt()
        val end = endpoints[1].toInt()
        return start..end
    }

    fun part1(input: List<String>): Int {
        var overlaps = 0

        for (line in input) {
            val ranges = line.split(',')
            val range1 = stringToRange(ranges[0])
            val range2 = stringToRange(ranges[1])
            if (range1 contains range2 || range2 contains range1) overlaps++
        }

        return overlaps
    }

    fun part2(input: List<String>): Int {
        var overlaps = 0

        for (line in input) {
            val ranges = line.split(',')
            val range1 = stringToRange(ranges[0])
            val range2 = stringToRange(ranges[1])
            if (range1 overlaps range2 || range2 overlaps range1) overlaps++
        }

        return overlaps
    }


    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
