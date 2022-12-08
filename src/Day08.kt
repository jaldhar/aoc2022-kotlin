fun MutableList<CharArray>.isVisible(startX: Int, startY: Int): Boolean {
    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    var los = mutableListOf<Boolean>()

    outer@ for (direction in directions) {
        val height = this[startX][startY]
        val dx = direction.first
        val dy = direction.second

        if (dx == -1) {
            if (startX != 0) {
                for (x in 0.rangeTo(startX - 1)) {
                    if (this[x][startY] >= height) {
                        los.add(false);
                        continue@outer
                    }
                }

            }
        } else if (dx == 1) {
            if (startX != this.lastIndex) {
                for (x in (startX + 1).rangeTo(this.lastIndex)) {
                    if (this[x][startY] >= height) {
                        los.add(false);
                        continue@outer
                    }
                }
            }
        } else if (dy == -1) {
            if (startY != 0) {
                for (y in 0.rangeTo(startY - 1)) {
                    if(this[startX][y] >= height) {
                        los.add(false);
                        continue@outer
                    }
                }
            }
        } else if (dy == 1) {
            if (startY != this[startX].lastIndex) {
                for (y in (startY + 1).rangeTo(this[startX].lastIndex)) {
                    if (this[startX][y] >= height) {
                        los.add(false);
                        continue@outer
                    }
                }
            }
        } else {
            los.add(false);
            continue@outer
        }

        los.add(true);
    }

    return los.any { it == true };
}

fun MutableList<CharArray>.countVisible(startX: Int, startY: Int): Int {
    val directions = listOf(Pair(-1, 0), Pair(0, 1), Pair(1, 0), Pair(0, -1))
    var los = mutableListOf<Int>()

    outer@ for (direction in directions) {
        val height = this[startX][startY]
        val dx = direction.first
        val dy = direction.second
        var count: Int = 0

        if (dx == -1) {
            if (startX != 0) {
                for (x in 0.rangeTo(startX - 1).reversed()) {
                    count++
                    if (this[x][startY] >= height) {
                        los.add(count)
                        continue@outer
                    }
                }

            }
        } else if (dx == 1) {
            if (startX != this.lastIndex) {
                for (x in (startX + 1).rangeTo(this.lastIndex)) {
                    count++
                    if (this[x][startY] >= height) {
                        los.add(count)
                        continue@outer
                    }
                }
            }
        } else if (dy == -1) {
            if (startY != 0) {
                for (y in 0.rangeTo(startY - 1).reversed()) {
                    count++
                    if(this[startX][y] >= height) {
                        los.add(count);
                        continue@outer
                    }
                }
            }
        } else if (dy == 1) {
            if (startY != this[startX].lastIndex) {
                for (y in (startY + 1).rangeTo(this[startX].lastIndex)) {
                    count++
                    if (this[startX][y] >= height) {
                        los.add(count);
                        continue@outer
                    }
                }
            }
        }

        los.add(count)
    }

    return los.fold(1) { product, n -> product * n }
}

fun main() {
    fun part1(input: List<String>): Int {
        val grid = mutableListOf<CharArray>()

        for (line in input) {
            grid.add(line.toCharArray())
        }

        var visible: Int = 0

        for (i in 0.rangeTo(grid.lastIndex)) {
            for (j in 0.rangeTo(grid[i].lastIndex)) {
                if (grid.isVisible(i, j)) {
                    visible++
                }
            }
        }

        return visible
    }

    fun part2(input: List<String>): Int {
        val grid = mutableListOf<CharArray>()

        for (line in input) {
            grid.add(line.toCharArray())
        }

        var scores = mutableListOf<Int>()

        for (i in 0.rangeTo(grid.lastIndex)) {
            for (j in 0.rangeTo(grid[i].lastIndex)) {
                scores.add(grid.countVisible(i, j))
            }
        }

        return scores.sorted().last()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 21)
    check(part2(testInput) == 8)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
