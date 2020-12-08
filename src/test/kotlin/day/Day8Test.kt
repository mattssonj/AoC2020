package day

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readInput

class Day8Test {

    private val exampleInput = """
        nop +0  | 1
        acc +1  | 2
        jmp +4  | 3
        acc +3  | 6
        jmp -3  | 7
        acc -99 |
        acc +1  | 4
        jmp -4  | 5
        acc +6  |
    """.trimIndent()

    @Test
    fun `From example 1`() {
        Day8(exampleInput.lines()).calculateFirst() shouldBe 5
    }

    @Test
    fun `From example 2`() {
        Day8(exampleInput.lines()).calculateSecond() shouldBe 8
    }

    @Test
    fun `Correct answer after turning in 1`() {
        val solution = Day8(readInput(8)).calculateFirst()
        solution shouldBe 1087
    }

    @Test
    fun `Correct answer after turning in 2`() {
        val solution = Day8(readInput(8)).calculateSecond()
        solution shouldBe 780
    }
}