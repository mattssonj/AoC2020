package day

class Day5(private val dirtyInput: List<String>) : Solver<Int> {

    override fun calculateFirst(): Int {
        return getAllIds().maxOrNull() ?: 0
    }

    override fun calculateSecond(): Int {
        return getAllIds()
                .sorted()
                .zipWithNext { a, b -> if (a + 1 != b) a + 1 else -1 }
                .maxOrNull() ?: 0
    }

    private fun getAllIds(): Sequence<Int> {
        return dirtyInput
                .asSequence()
                .map { BoardingPass(it).getId() }
    }
}

class BoardingPass(private val row: String, private val seat: String) {
    constructor(binarySpacePartitionValue: String) : this(binarySpacePartitionValue.take(7), binarySpacePartitionValue.takeLast(3))

    fun getId() = getRowAsInt() * 8 + getSeatAsInt()
    private fun getRowAsInt() = row.toBinaryString("B", "F").fromBinaryToInt()
    private fun getSeatAsInt() = seat.toBinaryString("R", "L").fromBinaryToInt()
}

private fun String.toBinaryString(ones: String, zeroes: String) = this.replace(ones, "1").replace(zeroes, "0")
private fun String.fromBinaryToInt() = Integer.parseInt(this, 2)