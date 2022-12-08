import kotlin.math.max
fun main() {
    fun makeGrid(area: List<String>): Array<IntArray> {
        val grid = Array(area[0].length) { IntArray(area.size) }

        for (row in area.indices) {
            for (column in area[row].indices) {
                grid[row][column] = area[row][column].digitToInt()
            }
        }

        return grid
    }

    fun part1(input: List<String>): Int {
        val grid = makeGrid(input)
        var visibleTrees = 4 * (grid.size - 1)

        for (i in 1 until grid.size - 1) {
            for (j in 1 until grid[i].size - 1) {
                val currentTree = grid[i][j]
                if (currentTree == 0) continue

                var isVisible = true

                for (k in 0 until i) {
                    if (grid[k][j] >= currentTree) {
                        isVisible = false
                        break
                    }
                }

                if (isVisible) {
                    visibleTrees++
                    continue
                }

                isVisible = true

                for (k in i + 1 until grid.size) {
                    if (grid[k][j] >= currentTree) {
                        isVisible = false
                        break
                    }
                }

                if (isVisible) {
                    visibleTrees++
                    continue
                }

                isVisible = true

                for (k in 0 until j) {
                    if (grid[i][k] >= currentTree) {
                        isVisible = false
                        break
                    }
                }
                if (isVisible) {
                    visibleTrees++
                    continue
                }

                isVisible = true

                for (k in j + 1 until grid[i].size) {
                    if (grid[i][k] >= currentTree) {
                        isVisible = false
                        break
                    }
                }

                if (isVisible) {
                    visibleTrees++
                    continue
                }
            }
        }

        return visibleTrees
    }

    fun part2(input: List<String>): Int {
        val grid = makeGrid(input)
        var scenicScore = 0

        for (i in 1 until grid.size - 1) {
            for (j in 1 until grid[i].size - 1) {
                val currentTree = grid[i][j]

                var top = 0
                for (k in i - 1 downTo 0) {
                    top++
                    if (grid[k][j] >= currentTree) break
                }

                var bottom = 0
                for (k in i + 1 until grid.size) {
                    bottom++
                    if (grid[k][j] >= currentTree) break
                }

                var left = 0
                for (k in j - 1 downTo 0) {
                    left++
                    if (grid[i][k] >= currentTree) break
                }

                var right = 0
                for (k in j + 1 until grid[i].size) {
                    right++
                    if (grid[i][k] >= currentTree) break
                }

                scenicScore = max(scenicScore, top * bottom * left * right)
            }
        }
        return scenicScore
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
