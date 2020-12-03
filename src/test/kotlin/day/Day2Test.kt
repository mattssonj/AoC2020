package day

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Day2Test {

    @Test
    fun `From example 1`() {
        val solution = Day2(exampleInput.lines()).calculateFirst()
        assertThat(solution).isEqualTo(2)
    }

    @Test
    fun `From example 2`() {
        val solution = Day2(exampleInput.lines()).calculateSecond()
        assertThat(solution).isEqualTo(1)
    }

    private val exampleInput = """
        1-3 a: abcde
        1-3 b: cdefg
        2-9 c: ccccccccc""".trimIndent()
}