

import kotlin.collections.first
import kotlin.collections.removeFirst

class Stacks {
    val stacks = mutableMapOf<Int, MutableList<Char>>()

    fun readDiagram(line: String) {
        val matches = """\[(\p{Upper})\]""".toRegex().findAll(line)

        for (match in matches) {
            val index = (match.range.first / 4) + 1 
            if (stacks[index] == null) {
                stacks[index] = mutableListOf<Char>()
            }
            stacks[index]!!.add(match.value.get(1))
        }
    }

    fun move(line: String) {
        val matches = """move (\d+) from (\d+) to (\d+)""".toRegex().find(line)
        val (quantity, from, to) = matches!!.destructured        

        for (i in 0 until quantity.toInt()) {
            stacks[to.toInt()]!!.add(0, stacks[from.toInt()]!!.removeFirst())
        }
    }

    fun moveAll(line: String) {
        val matches = """move (\d+) from (\d+) to (\d+)""".toRegex().find(line)
        val (quantity, from, to) = matches!!.destructured        

        val moving: List<Char> = stacks[from.toInt()]!!.take(quantity.toInt())
        stacks[to.toInt()]!!.addAll(0, moving)
        stacks[from.toInt()]!!.subList(0, quantity.toInt()).clear()
    }
    
    fun dump() {
        for (key in stacks.keys.toList().sorted()) {
            println("${key} ${stacks.get(key)!!.joinToString()}")
        }
    }

    fun top(): String {
        return stacks.keys.toList().sorted().map({ stacks[it]!!.first() }).joinToString("")
    }
}

fun main() {
    fun part1(input: List<String>): String {
        val stacks = Stacks()
        var task = stacks::readDiagram

        for (line in input) {
            if (line.isEmpty()) {
                task = stacks::move
                continue
            }
            task(line);    
        }

        return stacks.top()
    }

    fun part2(input: List<String>): String {
        val stacks = Stacks()

        var task = stacks::readDiagram

        for (line in input) {
            if (line.isEmpty()) {
                task = stacks::moveAll
                continue
            }
            task(line);    
        }
        return stacks.top()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == "CMZ")
    check(part2(testInput) == "MCD")

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
