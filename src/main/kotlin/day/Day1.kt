package day

class Day1(dirtyInput: List<String>): Solver<Int> {

    private val input: List<Int> = dirtyInput.map { it.toInt() }
    private val sumToReach = 2020

    override fun calculateFirst(): Int {

        input.forEachIndexed { index, i ->
            val corresponding = findCorresponding(index, i)
            if (corresponding != 0)
                return i * corresponding
        }

        return 0

    }

    override fun calculateSecond(): Int {

        input.forEachIndexed { indexI, i ->
            input.subList(indexI + 1, input.size).forEachIndexed { indexJ, j ->
                input.subList(indexJ + 1, input.size).forEach {
                    if (i + j + it == sumToReach)
                        return i * j * it
                }
            }
        }

        return 0
    }

    private fun findCorresponding(index: Int, value: Int): Int {
        return input
            .subList(index + 1, input.size)
            .firstOrNull { it + value == sumToReach }
            ?: 0
    }
}