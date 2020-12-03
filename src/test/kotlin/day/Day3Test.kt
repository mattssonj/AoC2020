package day

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import readInput

class Day3Test {

    private val exampleInput = """
        ..##.......
        #...#...#..
        .#....#..#.
        ..#.#...#.#
        .#...##..#.
        ..#.##.....
        .#.#.#....#
        .#........#
        #.##...#...
        #...##....#
        .#..#...#.#
    """.trimIndent()

    @Test
    fun `From example 1`() {
        val solution = Day3(exampleInput.lines()).calculateFirst()
        assertThat(solution).isEqualTo(7)
    }

    @Test
    fun `From example 2`() {
        val solution = Day3(exampleInput.lines()).calculateSecond()
        assertThat(solution).isEqualTo(336)
    }

    @Test
    fun `Correct answer after turning in 1`() {
        assertThat(Day3(readInput(3)).calculateFirst()).isEqualTo(247)
    }


    @Test
    fun `Correct answer after turning in 2`() {
        assertThat(Day3(readInput(3)).calculateSecond()).isEqualTo(2983070376)
    }
}