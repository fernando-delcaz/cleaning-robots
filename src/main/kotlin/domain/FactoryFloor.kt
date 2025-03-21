package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException
import domain.exceptions.TileAlreadyOccupiedException

class FactoryFloor(private val rows: Int, private val columns: Int) {

    private val occupiedPositions: MutableMap<Position, Robot> = mutableMapOf()

    fun clearTile(robot: Robot) {
        val currentRobot = occupiedPositions[robot.status.position]
        if (currentRobot != null && currentRobot !== robot) {
            throw TileAlreadyOccupiedException("The tile is occupied by another robot.")
        }
        occupiedPositions.remove(robot.status.position)
    }

    fun occupyTile(robot: Robot) {
        occupiedPositions[robot.status.position] = robot
    }

    fun isTileOccupied(position: Position): Boolean {
        if (isOutOfBoundaries(position)) {
            throw OutsideOfTheFactoryBoundariesException("You can’t query a position outside the factory grid boundaries")
        }

        return occupiedPositions.containsKey(position)
    }

    private fun isOutOfBoundaries(position: Position): Boolean {
        return position.x < 1 || position.x >= columns + 1 || position.y < 1 || position.y >= rows + 1 //Check the test example at the end of the exercise spec
    }

    fun updateTileContent(robot: Robot) {
        try {
            if (isTileOccupied(robot.status.position)) {
                throw TileAlreadyOccupiedException("A robot cannot land over another!")
            }
            occupyTile(robot)
        } catch (exception: ArrayIndexOutOfBoundsException) {
            throw OutsideOfTheFactoryBoundariesException("Robot placed outside grid boundaries!")
        }
    }
}