package day

class Day7(dirtyInput: List<String>) : Solver<Int> {

    private val BAG_NAME = "shiny gold"
    private val bagsWithSizes = dirtyInput
        .map { it.split(" ") }
        .map { it.windowed(4, 4) }
        .map { it.firstWithRest() }
        .map { (main, contained) -> parseMainBag(main) to contained.map { parseContainedBag(it) }.toSet() }
        .toMap()

    private fun parseMainBag(words: List<String>) = "${words[0]} ${words[1]}"
    private fun parseContainedBag(words: List<String>) = "${words[1]} ${words[2]}" to words[0].toInt()
    private fun List<List<String>>.firstWithRest() = this.first() to this.drop(1)

    override fun calculateFirst(): Int {
        return bagsWithSizes
            .filter { containsShinyGoldBag(it.key) }
            .size
    }

    override fun calculateSecond(): Int {
        return countContainingBags(BAG_NAME)
    }

    private fun containsShinyGoldBag(bagName: String): Boolean {
        val containingBags = getContainingBags(bagName)
        return if (containingBags.any { (name, _) -> name == BAG_NAME }) true
        else containingBags.any { containsShinyGoldBag(it.first) }
    }

    private fun countContainingBags(bagName: String): Int {
        val containingBags = getContainingBags(bagName)
        return if (containingBags.isEmpty()) 0
        else {
            containingBags
                .map { (name, size) -> (countContainingBags(name) + 1) * size }
                .sum()
        }
    }

    private fun getContainingBags(bagName: String) = bagsWithSizes.getOrDefault(bagName, emptySet())
}