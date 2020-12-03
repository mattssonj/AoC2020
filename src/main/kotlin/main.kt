import day.Solver
import io.ktor.client.*
import java.io.File

fun main() {

    val daysSolved = 3


    (1..daysSolved).forEach {
        collectInputIfNotExisting(it)
        println("Solutions for Day $it")
        val (part1, part2) = getSolutionsForDay(it)
        println("Part 1 = $part1")
        println("Part 2 = $part2")
    }

}

fun collectInputIfNotExisting(day: Int) {

    val fileName = "day$day.txt"
    val file = File(fileName)
    if (file.exists()) return

    val input = collectInput(day)

}

fun collectInput(day: Int) {

    val client = HttpClient()
    // curl https://adventofcode.com/2018/day/DAY/input --cookie "session=SESSION"

}

fun getSolutionsForDay(day: Int): Pair<String, String> {
    val dayObject = Class.forName("day.Day${day}")
        ?.getDeclaredConstructor(List::class.java)
        ?.newInstance(readInput(day)) as Solver<*>
    return Pair(dayObject.calculateFirst().toString(), dayObject.calculateSecond().toString())
}

fun readInput(day: Int): List<String> = File("src/main/resources/input/day$day.txt").readLines()