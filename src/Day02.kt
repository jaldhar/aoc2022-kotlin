
class RockPaperScissors {
    companion object {
        val shape = mapOf<String, Int>(
            "X" to 1,
            "Y" to 2,
            "Z" to 3,
        )

        val transform = mapOf<String, String>(
            "AX" to "Z",
            "AY" to "X",
            "AZ" to "Y",
        
            "BX" to "X",
            "BY" to "Y",
            "BZ" to "Z",
        
            "CX" to "Y",
            "CY" to "Z",
            "CZ" to "X",
        )
    
    
        val outcome = mapOf<String, Int>(
            "AX" to 3,
            "AY" to 6,
            "AZ" to 0,
        
            "BX" to 0,
            "BY" to 3,
            "BZ" to 6,
        
            "CX" to 6,
            "CY" to 0,
            "CZ" to 3,
        )

        fun play1(line: String): Int {
            var (his, mine) = line.split(" ")
    
            return RockPaperScissors.shape[mine]!! + RockPaperScissors.outcome["${his}${mine}"]!!
        }
    
        fun play2(line: String): Int {
            var (his, mine) = line.split(' ');
            mine = RockPaperScissors.transform["${his}${mine}"]!!;
    
            return RockPaperScissors.shape[mine]!! + RockPaperScissors.outcome["${his}${mine}"]!!
        }
    }

}

fun main() {

    fun part1(input: List<String>): Int {
        return input.map { RockPaperScissors.play1(it) }.sumOf { it }
    }

    fun part2(input: List<String>): Int {
        return input.map { RockPaperScissors.play2(it) }.sumOf { it }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
