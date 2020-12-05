package day

class Day5(private val dirtyInput: List<String>) : Solver<Int> {

    override fun calculateFirst(): Int {
        return getAllIds().maxOrNull() ?: 0
    }

    override fun calculateSecond(): Int {
        return getAllIds()
                .sorted()
                .chunked(2)
                .filter { if (it.size < 2) false else it[0] != it[1] - 1 }
                .map { (first, _) -> first + 1 }
                .first()
    }

    private fun getAllIds(): Sequence<Int> {
        return dirtyInput
                .asSequence()
                .map { Pair(it.take(7), it.takeLast(3)) }
                .map {
                    Pair(
                            it.first.replace("F", "0").replace("B", "1"),
                            it.second.replace("L", "0").replace("R", "1"))
                }
                .map { Pair(Integer.parseInt("${it.first}000", 2), Integer.parseInt(it.second, 2)) }
                .map { it.first + it.second }
    }

}