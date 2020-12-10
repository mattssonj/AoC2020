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
        return calculateAdapterConnections(input)
    }

    private val cachedPaths = mutableMapOf<Int, Long>()

    private fun calculateAdapterConnections(orderedAdapters: List<Int>): Long {
        if (isFinished(orderedAdapters)) return 1

        val possibleConnections = orderedAdapters.take(4)

        val validConnections = possibleConnections
            .drop(1)
            .filter { it - possibleConnections.first() <= 3 }

        return validConnections
            .mapIndexed { index, i -> getFromCacheOrCalculatePaths(i, orderedAdapters.drop(index + 1)) }
            .sum()
    }

    private fun isFinished(list: List<Int>) = list.size == 1

    private fun getFromCacheOrCalculatePaths(start: Int, list: List<Int>): Long {
        if (!cachedPaths.containsKey(start)) cachedPaths[start] = calculateAdapterConnections(list)
        return cachedPaths[start]!!
    }
}