package day

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day6Test {

    @Test
    fun `First example of part 2`() {
        Day6(listOf("abc")).calculateSecond() shouldBe 3
    }

    @Test
    fun `Second example of part 2`() {
        Day6(listOf("""a
    b
    c""")).calculateSecond() shouldBe 0
    }

    @Test
    fun `Third example of part 2`() {
        Day6(listOf("""ab
    ac""")).calculateSecond() shouldBe 1
    }

    @Test
    fun `Fourth example of part 2`() {
        Day6(listOf("""a
    a
    a
    a""")).calculateSecond() shouldBe 1
    }

}