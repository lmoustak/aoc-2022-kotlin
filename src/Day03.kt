fun main() {
    val items = mutableSetOf<Char>()
    for (c in 'a'..'z') items.add(c)
    for (c in 'A'..'Z') items.add(c)

    fun part1(input: List<String>): Int {
        var score = 0

        for (line in input) {
            val partition = line.length / 2
            val firstHalf = line.substring(0, partition)
            val firstHalfItems = firstHalf.toCharArray()

            val secondHalf = line.substring(partition)
            val commonsFound = mutableSetOf<Char>()
            for (c in secondHalf) {
                if (firstHalfItems.contains(c) && commonsFound.add(c)) score += items.indexOf(c) + 1
            }
        }

        return score
    }

    fun part2(input: List<String>): Int {
        var score = 0
        val size = input.size

        for (i in 0 until size step 3) {
            var commons = input[i].toCharSet()
            commons = commons.intersect(input[i + 1].toCharSet())
            commons = commons.intersect(input[i + 2].toCharSet())
            score += items.indexOf(commons.first()) + 1
        }
        return score
    }


    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
