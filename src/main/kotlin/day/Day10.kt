package day

class Day10(dirtyInput: List<String>) : Solver<Long> {

    private val input = (listOf("0") + dirtyInput) // adding the outlet
        .map { it.toInt() }
        .sorted()

    override fun calculateFirst(): Long {
        val numberOfOnes = input
            .zipWithNext()
            .map { it.second - it.first }
            .filter { it == 1 }
            .size.toLong()

        return numberOfOnes * (input.size - numberOfOnes)
    }

    override fun calculateSecond(): Long {
        return calculateAdapterConnections(0)
    }

    private val cachedConnections = mutableMapOf<Int, Long>()

    private fun calculateAdapterConnections(currentIndex: Int): Long {
        if (isFinished(currentIndex)) return 1
        val validAdapters = getNextValidAdapterIndexes(currentIndex)
        return validAdapters.map { getFromCacheOrCalculateConnections(it) }.sum()
    }

    private fun isFinished(index: Int) = input.size - 1 == index

    private fun getNextValidAdapterIndexes(currentIndex: Int): List<Int> {
        return nextIndexes(currentIndex)
            .filter { input[it] - input[currentIndex] <= 3 }
    }

    private fun nextIndexes(currentIndex: Int) =
        if (input.size <= currentIndex + 3) (currentIndex + 1 until input.size)
        else (currentIndex + 1 until currentIndex + 4)

    private fun getFromCacheOrCalculateConnections(start: Int): Long {
        if (!cachedConnections.containsKey(start)) cachedConnections[start] = calculateAdapterConnections(start)
        return cachedConnections[start]!!
    }
}