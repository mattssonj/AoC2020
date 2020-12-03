package day

import io.kotest.matchers.shouldBe
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
        solution shouldBe 7
    }

    @Test
    fun `From example 2`() {
        val solution = Day3(exampleInput.lines()).calculateSecond()
        solution shouldBe 336
    }

    @Test
    fun `Correct answer after turning in 1`() {
        val solution = Day3(readInput(3)).calculateFirst()
        solution shouldBe 247
    }


    @Test
    fun `Correct answer after turning in 2`() {
        val solution = Day3(readInput(3)).calculateSecond()
        solution shouldBe 2983070376
    }
}