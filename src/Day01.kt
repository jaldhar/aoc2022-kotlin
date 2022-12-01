fun main() {
    // convert the entries in [input[ to integers, provide the total calories
    // for each elf and return the top [upto] elves

    fun countCalories(input: List<String>, upto: Int): Int {
        val elves = mutableMapOf<Int, Int>()
        var elf = 1
        elves[elf] = 0
        for (line in input) {
            if (line.isEmpty()) {
                elf++;
                elves[elf] = 0
            } else {
                val calories = line.toInt()
                elves[elf] = elves[elf]!!.plus(calories)
            }
        }
    
        val top: List<Int> = elves
            .keys
            .sortedWith(compareByDescending { elves[it] })
            .slice(0 .. upto - 1)
        var result = 0
        for (e in top) {
            result += elves[e]!!
        }
    
        return result;
    }

    fun part1(input: List<String>): Int {
        return countCalories(input, 1);
    }

    fun part2(input: List<String>): Int {
        return countCalories(input, 3);
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
