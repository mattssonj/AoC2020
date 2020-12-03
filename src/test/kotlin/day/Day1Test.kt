package day

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day1Test {

    private val exampleInput = listOf(
        "1721",
        "979",
        "366",
        "299",
        "675",
        "1456"
    )

    @Test
    fun `From example 1`() {
        val result = Day1(exampleInput).calculateFirst()
        result shouldBe 514579
    }

    @Test
    fun `From example 2`() {
        val result = Day1(exampleInput).calculateSecond()
        result shouldBe 241861950
    }

}