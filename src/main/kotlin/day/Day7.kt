package day

class Day7(private val dirtyInput: List<String>): Solver<Int> {

    override fun calculateFirst(): Int {
        dirtyInput
                .map { it.split(" ") }
                .map { it.windowed(4, 4) }
                .map { it.takeFirstLeaveRest() }
                .map { it.first }

        return 0
    }

    override fun calculateSecond(): Int {
        return 0
    }

    private fun 
}

data class Bag(val name: String, val containedBags: Set<Bag> = emptySet())

private fun String.toBag(): Bag {
    val (mainBag, containedBags) = this
            .split(" ")
            .windowed(4, 4)
            .takeFirstLeaveRest()

    val bagName = mainBag.reduceIndexed { index, acc, s -> if (index < 1) "$acc $s" else acc }
    println(containedBags)

    return Bag(bagName)
}

private fun toContainedBag(string: String): Pair<Bag, Int> {
    return Pair(Bag(""), 1)
}

private fun List<List<String>>.takeFirstLeaveRest(): Pair<List<String>, List<List<String>> {
    return Pair(emptyList(), emptyList())
}