package day

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readInput

internal class Day9Test {

    private val preambleSize = 5
    private val exampleInput = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent()

    @Test
    fun `From example 1`() {
        Day9(exampleInput.lines(), preambleSize).calculateFirst() shouldBe 127
    }

    @Test
    fun `From example 2`() {
        Day9(exampleInput.lines(), preambleSize).calculateSecond() shouldBe 62
    }

    @Test
    fun `Correct answer after turning in 1`() {
        val solution = Day9(readInput(9)).calculateFirst()
        solution shouldBe 22406676
    }

    @Test
    fun `Correct answer after turning in 2`() {
        val solution = Day9(readInput(9)).calculateSecond()
        solution shouldBe 2942387
    }

}