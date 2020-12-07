package day

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import readInput

class Day7Test {

    private val exampleInput = """
        light red bags contain 1 bright white bag, 2 muted yellow bags.
        dark orange bags contain 3 bright white bags, 4 muted yellow bags.
        bright white bags contain 1 shiny gold bag.
        muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
        shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
        dark olive bags contain 3 faded blue bags, 4 dotted black bags.
        vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
        faded blue bags contain no other bags.
        dotted black bags contain no other bags.
    """.trimIndent()

    private val otherExample = """
        shiny gold bags contain 2 dark red bags.
        dark red bags contain 2 dark orange bags.
        dark orange bags contain 2 dark yellow bags.
        dark yellow bags contain 2 dark green bags.
        dark green bags contain 2 dark blue bags.
        dark blue bags contain 2 dark violet bags.
        dark violet bags contain no other bags.
        """.trimIndent()

    @Test
    fun `Part 1`() {
        Day7(exampleInput.lines()).calculateFirst() shouldBe 4
    }

    @Test
    fun `Part 2`() {
        Day7(exampleInput.lines()).calculateSecond() shouldBe 32
    }

    @Test
    fun `Part 2 other example`() {
        Day7(otherExample.lines()).calculateSecond() shouldBe 126
    }

    @Test
    fun `Correct answer after turning in 1`() {
        val solution = Day7(readInput(7)).calculateFirst()
        solution shouldBe 101
    }


    @Test
    fun `Correct answer after turning in 2`() {
        val solution = Day7(readInput(7)).calculateSecond()
        solution shouldBe 108636
    }

}