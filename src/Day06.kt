fun main() {
    fun part1(input: List<String>): Int {
        val chars = input[0].toCharArray()

        for (i in 3 until chars.size) {
            val charSet = setOf(chars[i - 3], chars[i - 2], chars[i - 1], chars[i])
            if (charSet.size == 4) return i + 1
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        val chars = input[0].toCharArray()
        val listOfChars = mutableListOf<Char>()

        for (i in 0 until 14) listOfChars.add(chars[i])

        for (i in 14..chars.size) {
            val distinctCount = listOfChars.distinct().count()
            if (distinctCount == 14) return i

            if (i < chars.size) {
                listOfChars.removeAt(0)
                listOfChars.add(chars[i])
            }
        }

        return -1
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
