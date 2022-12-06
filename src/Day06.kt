fun List<Char>.isUnique(): Boolean {
    return this.distinct().size == this.size
}

fun List<Char>.search(length: Int): Int {
    for (chunk in this.windowed(length).withIndex()) {
        if (chunk.value.isUnique()) {
            return chunk.index + length
        }
    }

    return 0

}

fun main() {
    fun part1(input: List<String>): Int {
        return input[0].toList().search(4)
    }

    fun part2(input: List<String>): Int {
        return input[0].toList().search(14)
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 19)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
