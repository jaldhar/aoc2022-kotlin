class Score {
    companion object {
        private val scores = mutableMapOf<Char, Int>()

        init {
            var v = 1;
            for (k in (('a' .. 'z') + ('A' .. 'Z'))) {
                scores[k] = v++
            }
        }

        fun get(v: Char): Int {
            return scores[v] ?: 0
        }
    }
}

fun main() {

    fun part1(input: List<String>): Int {
        var total = 0

        for (line in input) {
            val length = line.length / 2
            var contents = line.toCharArray()
            val rucksacks = listOf(contents.take(length), contents.takeLast(length))
            total += rucksacks[0].intersect(rucksacks[1]).map({ Score.get(it) }).sumOf { it }
        }

        return total;
    }

    fun part2(input: List<String>): Int {
        var total = 0
        input.chunked(3) { group ->
            val rucksacks = group.map({ it.toCharArray().toList() })
            total += rucksacks[0].intersect(rucksacks[1]).intersect(rucksacks[2])
                .map({ Score.get(it) }).sumOf { it }
        }
        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
