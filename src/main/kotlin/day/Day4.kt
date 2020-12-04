package day

private const val delimiter = "|||"

class Day4(private val dirtyInput: List<String>) : Solver<Int> {

    override fun calculateFirst(): Int {
        return dirtyInput
            .reduce { acc, s -> if (s.isBlank()) "$acc$delimiter" else "$acc $s" }
            .split(delimiter)
            .filter { it.hasRequiredFields() }
            .size
    }

    override fun calculateSecond(): Int {
        return dirtyInput
            .reduce { acc, s -> if (s.isBlank()) "$acc$delimiter" else "$acc $s" }
            .split(delimiter)
            .filter { it.hasRequiredFields() && it.hasValidFieldValues() }
            .size
    }
}

private fun String.hasRequiredFields(): Boolean {
    return this.contains("byr:") &&
            this.contains("iyr:") &&
            this.contains("eyr:") &&
            this.contains("hgt:") &&
            this.contains("hcl:") &&
            this.contains("ecl:") &&
            this.contains("pid:")
}

private fun String.hasValidFieldValues(): Boolean {
    return this.isValidByr() &&
            this.isValidEcl() &&
            this.isValidEyr() &&
            this.isValidHcl() &&
            this.isValidIyr() &&
            this.isValidHgt() &&
            this.isValidPid()
}

private fun String.isValidByr() = this.extractValue("byr").toInt() in 1920..2002
private fun String.isValidIyr() = this.extractValue("iyr").toInt() in 2010..2020
private fun String.isValidEyr() = this.extractValue("eyr").toInt() in 2020..2030
private fun String.isValidHcl() = this.extractValue("hcl").contains(Regex("^#{1}[a-f0-9]+\$"))
private fun String.isValidPid() = this.extractValue("pid").contains(Regex("^[0-9]{9}\$"))
private fun String.isValidEcl() =
    this.extractValue("ecl").contains(Regex("^(brn|amb|blu|gry|grn|hzl|oth)\$"))

private fun String.isValidHgt(): Boolean {
    val hgt = this.extractValue("hgt")
    if (hgt.endsWith("in")) return hgt.removeSuffix("in").toInt() in 59..76
    if (hgt.endsWith("cm")) return hgt.removeSuffix("cm").toInt() in 150..193
    return false
}

private fun String.extractValue(key: String) = this
    .split(" ")
    .first { it.startsWith(key, ignoreCase = true) }
    .removePrefix("$key:")