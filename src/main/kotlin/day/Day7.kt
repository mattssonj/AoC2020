package day

class Day7(private val dirtyInput: List<String>) : Solver<Int> {

    private val BAG_NAME = "shiny gold"
    private lateinit var bags: Map<String, Set<String>>
    private lateinit var bagsWithSizes: Map<String, Set<Pair<String, Int>>>

    override fun calculateFirst(): Int {
        bags = dirtyInput
            .map { it.split(" ") }
            .map { it.windowed(4, 4) }
            .map { it.takeFirstLeaveRest() }
            .map { (main, contained) ->
                Pair(
                    parseMainBag(main),
                    contained.map { parseContainedBag(it).first }.toSet()
                )
            }
            .toMap()

        return bags.filter { containsBag(it.key) }
            .size
    }

    override fun calculateSecond(): Int {
        bagsWithSizes = dirtyInput
            .map { it.split(" ") }
            .map { it.windowed(4, 4) }
            .map { it.takeFirstLeaveRest() }
            .map { (main, contained) -> Pair(parseMainBag(main), contained.map { parseContainedBag(it) }.toSet()) }
            .toMap()

        return countContainingBags(BAG_NAME)
    }

    private fun parseMainBag(words: List<String>): String {
        if (words.isEmpty()) return ""
        return "${words[0]} ${words[1]}"
    }

    private fun parseContainedBag(words: List<String>): Pair<String, Int> {
        if (words.isEmpty()) return Pair("", 0)
        return Pair("${words[1]} ${words[2]}", words[0].toInt())
    }

    private fun containsBag(bagName: String): Boolean {
        val contained = bags.getOrDefault(bagName, emptySet())
        return if (contained.contains(BAG_NAME)) true
        else contained.any { containsBag(it) }
    }

    private fun countContainingBags(bagName: String): Int {
        val containingBags = bagsWithSizes.getOrDefault(bagName, emptySet())
        return if (containingBags.isEmpty()) 0
        else {
            containingBags
                .map { (countContainingBags(it.first) + 1) * it.second }
                .sum()
        }
    }
}

private fun List<List<String>>.takeFirstLeaveRest(): Pair<List<String>, List<List<String>>> {
    return Pair(this[0], this.subList(1, this.size))

}