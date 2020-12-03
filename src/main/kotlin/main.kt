import day.Day1
import day.Day2
import day.Day3
import java.io.File

fun main() {

    println("Result of Day 1 part 1 = ${Day1(readInput(1)).calculateFirst()}")
    println("Result of Day 1 part 2 = ${Day1(readInput(1)).calculateSecond()}")

    println("Result of Day 2 part 1 = ${Day2(readInput(2)).calculateFirst()}")
    println("Result of Day 2 part 2 = ${Day2(readInput(2)).calculateSecond()}")

    println("Result of Day 3 part 1 = ${Day3(readInput(3)).calculateFirst()}")
    println("Result of Day 3 part 2 = ${Day3(readInput(3)).calculateSecond()}")

}

fun readInput(day: Int): List<String> = File("src/main/resources/input/day$day.txt").readLines()