package day

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day5Test {

    /*BFFFBBFRRR: row 70, column 7, seat ID 567.
    FFFBBBFRRR: row 14, column 7, seat ID 119.
    BBFFBBFRLL: row 102, column 4, seat ID 820.*/

    @Test
    fun `From example 1`() {
        Day5(listOf("BFFFBBFRRR")).calculateFirst() shouldBe 567
    }

    @Test
    fun `From example 2`() {
        Day5(listOf("FFFBBBFRRR")).calculateFirst() shouldBe 119
    }

    @Test
    fun `From example 3`() {
        Day5(listOf("BBFFBBFRLL")).calculateFirst() shouldBe 820
    }
}