package day

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day4Test {

    @Test
    fun `From Example 1`() {
        val input = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm
        """.trimIndent()

        Day4(input.split("\\n\\n")).calculateFirst() shouldBe 1

    }

    @Test
    fun `From Example 2`() {
        val input = """
            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929
        """.trimIndent()

        Day4(input.split("\\n\\n")).calculateFirst() shouldBe 0
    }

    @Test
    fun `From Example 3`() {
        val input = """
            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm
        """.trimIndent()

        Day4(input.split("\\n\\n")).calculateFirst() shouldBe 1
    }

    @Test
    fun `From Example 4`() {
        val input = """
            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()

        Day4(input.split("\\n\\n")).calculateFirst() shouldBe 0
    }

    @Test
    fun `All cases together`() {
        Day4(allExamples.lines()).calculateFirst() shouldBe 2
    }
}

private val allExamples = """
    ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
    byr:1937 iyr:2017 cid:147 hgt:183cm

    iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
    hcl:#cfa07d byr:1929

    hcl:#ae17e1 iyr:2013
    eyr:2024
    ecl:brn pid:760753108 byr:1931
    hgt:179cm

    hcl:#cfa07d eyr:2025 pid:166559648
    iyr:2011 ecl:brn hgt:59in
""".trimIndent()