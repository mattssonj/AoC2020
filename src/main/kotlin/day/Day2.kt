package day

class Day2(dirtyInput: List<String>) {

    private val input = dirtyInput.map {
        val triple = it.split(" ")
        Triple(triple[0], triple[1], triple[2])
    }

    fun calculateFirst(): Int {
        return input.map { Pair(it.third, Policy.toMinMaxPolicy(it)) }
            .filter { (password, policy) -> policy.isValid(password) }
            .size
    }

    fun calculateSecond(): Int {
        return input.map { Pair(it.third, Policy.toCorrectCharAtPolicy(it)) }
            .filter { (password, policy) -> policy.isValid(password) }
            .size
    }
}

sealed class Policy(policyData: String, character: String) {
    protected val policyNumbers = policyData.split("-").map { it.toInt() }
    protected val policyCharacter = character.replace(":", "")
    abstract fun isValid(password: String): Boolean

    companion object {
        fun toMinMaxPolicy(passwordInfo: Triple<String, String, String>): Policy {
            return MinMaxPolicy(passwordInfo.first, passwordInfo.second)
        }

        fun toCorrectCharAtPolicy(passwordInfo: Triple<String, String, String>): Policy {
            return CorrectCharAtPolicy(passwordInfo.first, passwordInfo.second)
        }
    }
}

class MinMaxPolicy(minMax: String, character: String) : Policy(minMax, character) {
    override fun isValid(password: String): Boolean {
        val nCharacters = password.filter { policyCharacter.contains(it) }.length
        return nCharacters >= policyNumbers[0] && nCharacters <= policyNumbers[1]
    }
}

class CorrectCharAtPolicy(minMax: String, character: String) : Policy(minMax, character) {
    private var password: String = ""

    override fun isValid(password: String): Boolean {
        this.password = password
        return isCharAtIndex(policyNumbers[0]) xor isCharAtIndex(policyNumbers[1])
    }

    private fun isCharAtIndex(index: Int): Boolean {
        val correctIndex = index - 1
        if (isOutOfBounds(correctIndex)) return false
        return policyCharacter.contains(password.elementAt(correctIndex))
    }

    private fun isOutOfBounds(index: Int): Boolean {
        return index < 0 || index >= password.length
    }
}