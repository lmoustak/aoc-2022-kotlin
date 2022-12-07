abstract class DirectoryItem(open val name: String, open val parent: Directory?) {
    abstract fun computeSize(): Int
}

data class File(override val name: String, val size: Int, override val parent: Directory) :
    DirectoryItem(name, parent) {
    override fun computeSize(): Int = size
}

data class Directory(override val name: String, override val parent: Directory? = null) :
    DirectoryItem(name, parent) {
    val children: MutableSet<DirectoryItem> = mutableSetOf()
    override fun computeSize(): Int = children.sumOf { it.computeSize() }
}

fun main() {
    val cdPattern = Regex("""\$ cd (\S+)""")
    val lsPattern = "$ ls"
    val filePattern = Regex("""(\d+) (\S+)""")
    val dirPattern = Regex("""dir (\S+)""")

    fun walk(input: List<String>): Directory {
        val root = Directory("/")
        var currentDirectory: Directory = root

        var i = 0;
        while (i < input.size) {
            val command = input[i].trim()

            if (cdPattern.matches(command)) {
                val dir = cdPattern.matchEntire(command)!!.groupValues[1]
                currentDirectory = when (dir) {
                    "/" -> root
                    ".." -> currentDirectory.parent!!
                    else -> currentDirectory.children.find { it.name == dir } as Directory
                }

                i++
            } else if (lsPattern == command) {
                var item = input[++i].trim()

                while (!item.startsWith('$')) {
                    if (filePattern.matches(item)) {
                        val matchGroups = filePattern.matchEntire(item)!!.groupValues
                        val fileName = matchGroups[2]
                        val fileSize = matchGroups[1].toInt()

                        currentDirectory.children.addIfNotPresent(
                            { it.name == fileName },
                            File(fileName, fileSize, currentDirectory)
                        )
                    } else if (dirPattern.matches(item)) {
                        val name = dirPattern.matchEntire(item)!!.groupValues[1]
                        currentDirectory.children.addIfNotPresent(
                            { it.name == name },
                            Directory(name, currentDirectory)
                        )
                    } else {
                        throw IllegalArgumentException("'$command' is not valid!")
                    }

                    if (++i >= input.size) break
                    item = input[i].trim()
                }

            } else {
                throw IllegalArgumentException("'$command' is not valid!")
            }
        }

        return root
    }

    fun part1(input: List<String>): Int {
        fun computeSize(directory: Directory): Int {
            var totalSize = directory.computeSize()
            for (child in directory.children.filterIsInstance<Directory>()) {
                val size = computeSize(child)
                if (size <= 100_000) totalSize += size
            }

            return totalSize
        }

        val root = walk(input)

        return computeSize(root)
    }

    fun part2(input: List<String>): Int {
        return -1
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
