package day

private const val delimiter = "|||"

class Day6(private val dirtyInput: List<String>) : Solver<Int> {

    override fun calculateFirst(): Int {
        return dirtyInput
                .reduce { acc, s -> if (s.isBlank()) "$acc$delimiter" else "$acc $s" }
                .split(delimiter)
                .map {
                    it
                            .replace(" ", "")
                            .toSet()
                            .size
                }
                .sum()
    }

    override fun calculateSecond(): Int {
        return dirtyInput
                .reduce { acc, s -> if (s.isBlank()) "$acc$delimiter" else "$acc $s" }
                .split(delimiter)
                .map { allAnswers ->
                    allAnswers
                            .split(" ")
                            .filter { it.isNotBlank() }
                            .map { it.toSet() }
                            .reduce { acc, set -> acc intersect set }
                }
                .map { it.size }
                .sum()
    }
}