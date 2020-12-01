import day.Day1
import java.io.File

fun main() {

    val d1p1 = Day1(readInput(1)).calculateFirst()
    val d1p2 = Day1(readInput(1)).calculateSecond()
    println("result of day 1 part 1 = $d1p1 and part 2 = $d1p2")


}

fun readInput(day: Int): List<String> = File("src/main/resources/input/day$day.txt").readLines()