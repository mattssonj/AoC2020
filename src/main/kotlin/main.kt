import day.Solver
import org.http4k.client.JavaHttpClient
import org.http4k.core.Method
import org.http4k.core.Request
import java.io.File

const val inputPath = "src/main/resources/input/"
private val client = JavaHttpClient()

fun main() {
    val daysSolved = 10
    val onlyPrintLatest = true

    (1..daysSolved).forEach {
        collectInputIfNotExisting(it)
        if (onlyPrintLatest && it != daysSolved) return@forEach
        println("Solutions for Day $it")
        val (part1, part2) = getSolutionsForDay(it)
        println("Part 1 = $part1")
        println("Part 2 = $part2")
    }
}

fun collectInputIfNotExisting(day: Int) {
    val fileName = "${inputPath}day$day.txt"
    val file = File(fileName)
    if (file.exists()) return

    val input = collectInput(day)
    file.writeText(input)
}

fun collectInput(day: Int): String {
    val request = Request(Method.GET, "https://adventofcode.com/2020/day/$day/input")
        .header("Cookie", "session=${Environment.SESSION}")
    val response = client.invoke(request)
    return response.bodyString()
}

fun getSolutionsForDay(day: Int): Pair<String, String> {
    val dayObject = Class.forName("day.Day${day}")
        ?.getDeclaredConstructor(List::class.java)
        ?.newInstance(readInput(day)) as Solver<*>
    return Pair(dayObject.calculateFirst().toString(), dayObject.calculateSecond().toString())
}

fun readInput(day: Int): List<String> = File(inputPath + "day$day.txt").readLines()