package day


class Day9(dirtyInput: List<String>, private val preambleSize: Int) : Solver<Long> {

    // Used for reflection in Main
    constructor(dirtyInput: List<String>) : this(dirtyInput, 25)

    private val xmas = dirtyInput.map { it.toLong() }

    override fun calculateFirst(): Long {
        val preamble = Preamble(xmas.take(preambleSize))
        xmas.drop(preambleSize).forEach {
            if (!preamble.canSum(it)) return it
            preamble.add(it)
        }
        return 0
    }

    override fun calculateSecond(): Long {
        val numberToFind = calculateFirst()
        (0 until xmas.indexOf(numberToFind)).forEach {
            val indexOfSolution = findWeakness(it, numberToFind)
            if (indexOfSolution != -1) {
                return xmas[it] + xmas[indexOfSolution]
            }
        }
        return 0
    }

    private fun findWeakness(startIndex: Int, sumToReach: Long): Int {
        var sum = 0L
        xmas.drop(startIndex).forEachIndexed { index, l ->
            sum += l
            if (sum == sumToReach) return index + startIndex - 1 // -1 to correct index from
            if (sum > sumToReach) return -1
        }
        return -1
    }
}

class Preamble(initialValue: List<Long>) {

    private var preamble = initialValue.toMutableList()

    fun add(toAdd: Long) {
        preamble = preamble.drop(1).toMutableList()
        preamble.add(toAdd)
    }

    fun canSum(toSum: Long): Boolean {
        preamble.forEachIndexed { index, i ->
            preamble.drop(index + 1).forEach {
                if (i + it == toSum) return true
            }
        }
        return false
    }

}

