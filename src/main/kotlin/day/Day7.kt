package day

class Day7(dirtyInput: List<String>) : Solver<Int> {

    private val BAG_NAME = "shiny gold"
    private val bagsWithSizes = dirtyInput
        .map { it.split(" ") }
        .map { it.windowed(4, 4) }
        .map { it.takeFirstLeaveRest() }
        .map { (main, contained) -> parseMainBag(main) to contained.map { parseContainedBag(it) }.toSet() }
        .toMap()

    private fun parseMainBag(words: List<String>) = "${words[0]} ${words[1]}"
    private fun parseContainedBag(words: List<String>) = "${words[1]} ${words[2]}" to words[0].toInt()
    private fun List<List<String>>.takeFirstLeaveRest() = this[0] to this.subList(1, this.size)

    override fun calculateFirst(): Int {
        return bagsWithSizes
            .filter { containsBag(it.key) }
            .size
    }

    override fun calculateSecond(): Int {
        return countContainingBags(BAG_NAME)
    }

    private fun containsBag(bagName: String): Boolean {
        val contained = bagsWithSizes.getOrDefault(bagName, emptySet())
        return if (contained.any { (bagName, _) -> bagName == BAG_NAME }) true
        else contained.any { containsBag(it.first) }
    }

    private fun countContainingBags(bagName: String): Int {
        val containingBags = bagsWithSizes.getOrDefault(bagName, emptySet())
        return if (containingBags.isEmpty()) 0
        else {
            containingBags
                .map { (bagName, size) -> (countContainingBags(bagName) + 1) * size }
                .sum()
        }
    }
}