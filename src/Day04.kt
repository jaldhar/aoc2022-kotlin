fun main() {

    fun String.parse(): Pair<IntRange, IntRange> {
        val matches = """(\d+)\-(\d+)\,(\d+)\-(\d+)""".toRegex().find(this)
        val (a1, a2, b1, b2) = matches!!.destructured
        val first = IntRange(a1.toInt(), a2.toInt())
        val second = IntRange(b1.toInt(), b2.toInt())

        return Pair(first, second)
    }
 
    fun part1(input: List<String>): Int {
        var total = 0

        for (line in input) {
            val (first, second) = line.parse()
  
            if (first.all { second.contains(it) } || second.all { first.contains(it) }) {
                total++;
            }
        }

        return total;
    }

    fun part2(input: List<String>): Int {
        var total = 0

        for (line in input) {
            val (first, second) = line.parse()
  
            if (!first.intersect(second).isEmpty()) {
                total++
            }
        }

        return total
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
