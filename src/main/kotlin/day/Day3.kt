package day

class Day3(private val map: List<String>) : Solver<Long> {

    private val maxWidth = map[0].length
    private val maxDepth = map.size
    private val moves = listOf(
        Move(1, 1),
        Move(3, 1),
        Move(5, 1),
        Move(7, 1),
        Move(1, 2)
    )

    private var currentPosition = Position(0, 0)

    override fun calculateFirst(): Long {
        return moveDownTheMapAndFindTrees(1).toLong()
    }

    override fun calculateSecond(): Long {
        return (moves.indices)
            .map { moveDownTheMapAndFindTrees(it) }
            .map { it.toLong() } // The value gets to big for an Int
            .reduce { acc, i -> acc * i }
    }

    private fun moveDownTheMapAndFindTrees(indexOfMoveToUse: Int): Int {
        resetPosition()
        var numbersOfTreesFound = 0
        while (isNotBelowMap()) {
            if (isCurrentPositionATree())
                numbersOfTreesFound++
            move(indexOfMoveToUse)
        }
        return numbersOfTreesFound
    }

    private fun resetPosition() {
        currentPosition = Position(0, 0)
    }

    private fun isNotBelowMap(): Boolean {
        return currentPosition.y < maxDepth
    }

    private fun isCurrentPositionATree(): Boolean {
        return map[currentPosition.y].elementAt(currentPosition.x) == '#'
    }

    private fun move(indexOfMove: Int) {
        val activeMove = moves[indexOfMove]
        currentPosition = Position(currentPosition.x + activeMove.stepX, currentPosition.y + activeMove.stepY)
        if (isOutOfMap()) currentPosition = currentPosition.copy(x = currentPosition.x - maxWidth)
    }

    private fun isOutOfMap(): Boolean {
        return currentPosition.x >= maxWidth
    }
}

data class Position(val x: Int, val y: Int)
data class Move(val stepX: Int, val stepY: Int)