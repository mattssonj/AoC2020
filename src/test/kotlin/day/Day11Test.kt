package day

import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day11Test {

    private val exampleInput = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent()

    @Test
    fun `From example 1`() {
        Day11(exampleInput.lines()).calculateFirst() shouldBe 37
    }

}