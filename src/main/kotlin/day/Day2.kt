package day

class Day2(dirtyInput: List<String>) {

private val input = dirtyInput.map {
    val triple = it.split(" ")
    Triple(triple[0], triple[1], triple[2])
}


    fun calculateFirst(): Int {
        return input.filterInvalidPasswords().size
    }


}

private fun List<Triple<String, String, String>>.filterInvalidPasswords(): List<Triple<String, String, String>> {
    return this.filter { isValid(it) }
}

private fun isValid(dbRow: Triple<String, String, String>): Boolean {
    val minMax = dbRow.first.split("-").map { it.toInt() }
    val char = dbRow.second.replace(":", "")

    val nChars = dbRow.third.filter { it + "" == char }.length
    return nChars >= minMax[0] && nChars <= minMax[1]
}