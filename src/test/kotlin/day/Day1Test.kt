package day

import org.assertj.core.api.Assertions.assertThat
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
        assertThat(result).isEqualTo(514579)
    }

    @Test
    fun `From example 2`() {
        val result = Day1(exampleInput).calculateSecond()
        assertThat(result).isEqualTo(241861950)
    }

}