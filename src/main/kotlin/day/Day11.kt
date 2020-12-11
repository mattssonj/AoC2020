package day

class Day11(private val dirtyInput: List<String>) : Solver<Int> {

    override fun calculateFirst(): Int {
        val model = Model(dirtyInput, shouldBeOccupiedPart1)
        do {
            val changes = model.stabilize()
        } while (changes != 0)
        return model.getNumberOfOccupiedSeats()
    }

    override fun calculateSecond(): Int {
        val model = Model(dirtyInput, shouldBeOccupiedPart2)
        do {
            val changes = model.stabilize()
        } while (changes != 0)
        return model.getNumberOfOccupiedSeats()
    }
}

class Model(input: List<String>, private val shouldBeOccupied: (Seat, WaitingArea) -> Boolean) {

    private var currentStateOfWaitingArea = WaitingArea(input)

    fun stabilize(): NumberOfChanges {
        val currentlyOccupiedSeats = currentStateOfWaitingArea.getNumberOfOccupiedSeats()
        val seatsToBeOccupied = currentStateOfWaitingArea.getAllPossibleSeats()
            .filter { shouldBeOccupied(it, currentStateOfWaitingArea) }
            .toSet()

        currentStateOfWaitingArea.occupyOnly(seatsToBeOccupied)
        return currentlyOccupiedSeats - seatsToBeOccupied.size
    }

    fun getNumberOfOccupiedSeats(): Int {
        return currentStateOfWaitingArea.getNumberOfOccupiedSeats()
    }

}

class WaitingArea(initialValue: List<String>) {

    private var floor = initialValue.map { it.toCharArray() }

    fun getAllPossibleSeats(): Set<Seat> {
        return floor
            .flatMapIndexed { y: Int, row: CharArray ->
                row
                    .toList()
                    .mapIndexedNotNull { x, seatValue ->
                        if (seatValue != Floor) Seat(Position(x, y), seatValue)
                        else null
                    }
            }
            .toSet()
    }

    fun findNeighbours(pos: Position): Set<Seat> {
        return getNeighbouringPositions(pos)
            .filter { !isOutOfBounds(it) }
            .map { Seat(it, getValueOfPosition(it)) }
            .toSet()
    }

    fun findSeatsThatCanBeSeen(pos: Position): Set<Seat> {
        return getNeighbouringPositions(Position(0, 0))
            .mapNotNull { seeInDirection(it.x, it.y, pos) }
            .map { Seat(it, getValueOfPosition(it)) }
            .toSet()
    }

    private fun getNeighbouringPositions(startPos: Position): Set<Position> {
        return (-1 + startPos.x..1 + startPos.x)
            .flatMap { x -> (-1 + startPos.y..1 + startPos.y).map { y -> Position(x, y) } }
            .minus(startPos)
            .toSet()
    }

    private tailrec fun seeInDirection(incX: Int, incY: Int, from: Position): Position? {
        val newPos = Position(from.x + incX, from.y + incY)
        if (isOutOfBounds(newPos)) return null
        if (floor[newPos.y][newPos.x] == Occupied) return newPos
        if (floor[newPos.y][newPos.x] == EmptySeat) return null
        return seeInDirection(incX, incY, newPos)
    }

    private fun isOutOfBounds(pos: Position): Boolean {
        return pos.x < 0 || pos.x == floor.first().size || pos.y < 0 || pos.y == floor.size
    }

    private fun getValueOfPosition(pos: Position): Char {
        return floor[pos.y][pos.x]
    }

    fun occupyOnly(seatsToBeOccupied: Set<Seat>) {
        val positionsToBeOccupied = seatsToBeOccupied.map { it.position }
        val newFloor = floor.mapIndexed { y, row ->
            row.mapIndexed { x, seatValue ->
                when {
                    seatValue == Floor -> Floor
                    positionsToBeOccupied.contains(Position(x, y)) -> Occupied
                    else -> EmptySeat
                }
            }.toCharArray()
        }
        floor = newFloor
    }

    fun getNumberOfOccupiedSeats(): Int {
        return floor.map { row -> row.filter { seatValue -> seatValue == Occupied }.size }.sum()
    }

}

val shouldBeOccupiedPart1 = fun(seat: Seat, waitingArea: WaitingArea): Boolean {
    val currentValue = seat.value
    if (currentValue == EmptySeat && (calculateOccupiedNeighbours(seat.position, waitingArea) == 0)) return true
    if (currentValue == Occupied && calculateOccupiedNeighbours(seat.position, waitingArea) < 4) return true
    return false
}

private fun calculateOccupiedNeighbours(seatPos: Position, waitingArea: WaitingArea): Int {
    return waitingArea.findNeighbours(seatPos)
        .filter { it.value == Occupied }
        .size
}

val shouldBeOccupiedPart2 = fun(seat: Seat, waitingArea: WaitingArea): Boolean {
    val currentValue = seat.value
    val seatsSeen = waitingArea.findSeatsThatCanBeSeen(seat.position)
    if (currentValue == EmptySeat && seatsSeen.isEmpty()) return true
    if (currentValue == Occupied && seatsSeen.size < 5) return true
    return false
}

data class Seat(val position: Position, val value: Char)
typealias NumberOfChanges = Int

const val Floor = '.'
const val EmptySeat = 'L'
const val Occupied = '#'