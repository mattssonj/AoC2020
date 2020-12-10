package day

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readInput

internal class Day10Test {

    private val example1 = """
        16
        10
        15
        5
        1
        11
        7
        19
        6
        12
        4
    """.trimIndent()

    private val example2 = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent()

    @Test
    fun `Example 1 with exampledata1`() {
        Day10(example1.lines()).calculateFirst() shouldBe 7 * 5
    }

    @Test
    fun `Example 1 with exampledata2`() {
        Day10(example2.lines()).calculateFirst() shouldBe 22 * 10
    }

    @Test
    fun `Example 2 with exampledata1`() {
        Day10(example1.lines()).calculateSecond() shouldBe 8
    }

    @Test
    fun `Example 2 with exampledata2`() {
        Day10(example2.lines()).calculateSecond() shouldBe 19208
    }

    @Test
    fun `Correct answer after turning in 1`() {
        val solution = Day10(readInput(10)).calculateFirst()
        solution shouldBe 2312
    }

    @Test
    fun `Correct answer after turning in 2`() {
        val solution = Day10(readInput(10)).calculateSecond()
        solution shouldBe 12089663946752
    }


}