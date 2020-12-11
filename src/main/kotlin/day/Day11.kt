package day

import java.lang.Integer.min
import kotlin.math.max

class Day11(private val dirtyInput: List<String>) : Solver<Int> {

    override fun calculateFirst(): Int {
        val model = Model(dirtyInput)
        do {
            val changes = model.stabilize()
        } while (changes != 0)
        return model.getNumberOfOccupiedSeats()
    }

    override fun calculateSecond(): Int {
        return 0
    }
}

class Model(input: List<String>) {

    private val width = input.first().length
    private val height = input.size
    private val floor = input.map { it.toCharArray() }

    fun stabilize(): NumberOfChanges {
        var numberOfChanges = 0
        floor.indices.forEach { y ->
            floor[y].indices.forEach { x ->
                val currentPos = Position(x, y)
                val currentSeatValue = getSeat(currentPos)
                val nextSeatValue =
                        when {
                            currentSeatValue == Floor -> currentSeatValue
                            isHappy(currentPos) -> Occupied
                            else -> EmptySeat
                        }
                /*if (isHappy(currentPos)) Occupied
                else if (currentSeatValue == Floor) currentSeatValue
                else EmptySeat*/
                if (currentSeatValue != nextSeatValue) numberOfChanges++
                floor[currentPos.y][currentPos.x] = nextSeatValue
            }
        }
        printCurrentState()
        return numberOfChanges
    }

    fun getNumberOfOccupiedSeats(): Int {
        var occupied = 0
        floor.forEach {
            it.forEach {
                if (it == Occupied) occupied++
            }
        }
        return occupied
    }

    private fun isHappy(seatPos: Position): Boolean {
        if (floor[seatPos.y][seatPos.x] == EmptySeat) return true
        return calculateOccupiedNeighbours(seatPos) >= 4
    }

    private fun calculateOccupiedNeighbours(seatPos: Position): Int {
        var occupied = 0
        findNeighbours(seatPos).forEach {
            if (floor[it.y][it.x] == Occupied) occupied++
        }
        return occupied
    }

    private fun findNeighbours(current: Position): List<Position> {
        val neighbours = mutableListOf<Position>()
        (max(0, current.y - 1)..min(height - 1, current.y + 1)).forEach { y ->
            (max(0, current.x - 1)..min(width - 1, current.x + 1)).forEach { x ->
                val pos = Position(x, y)
                if (pos != current) neighbours.add(pos)
            }
        }
        return neighbours
    }

    private fun getSeat(pos: Position): Char {
        return floor[pos.y][pos.x]
    }

    private fun printCurrentState() {
        println()
        println()
        println()
        floor.forEach {
            println(it)
        }
        println()
        println()
        println()
    }
}

typealias NumberOfChanges = Int

const val Floor = '.'
const val EmptySeat = 'L'
const val Occupied = '#'