package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException
import domain.exceptions.TileAlreadyOccupiedException

class Factory(private val rows: Int, private val columns: Int) {

    var factoryFloor = FactoryFloor(rows, columns);

    fun canIMoveTo(position: Position): Boolean {
        return !factoryFloor.isTileOccupied(position);
    }

    internal fun updateRobotStatus(robot: Robot, desiredStatus: Status): Status {
        val initialRobotStatus = robot.status;

        try {
            factoryFloor.clearTile(robot)
            robot.status = desiredStatus
            updateTileContent(robot)
            return robot.status
        } catch (exception: OutsideOfTheFactoryBoundariesException) {
            print("Robot cannot go out of the factory grid boundaries!")
            robot.status = initialRobotStatus
            updateTileContent(robot);
            return initialRobotStatus
        } catch (exception: TileAlreadyOccupiedException) {
            print("Tile was already occupied! Skipping movement")
            robot.status = initialRobotStatus
            updateTileContent(robot);
            return initialRobotStatus
        }
    }

    private fun updateTileContent(robot: Robot) {
        try {
            if (factoryFloor.isTileOccupied(robot.status.position)) {
                throw TileAlreadyOccupiedException("A robot cannot land over another!")
            }
            factoryFloor.occupyTile(robot)
        } catch (exception: ArrayIndexOutOfBoundsException) {
            throw OutsideOfTheFactoryBoundariesException("Robot placed outside grid boundaries!")
        }
    }
}

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
        return position.x < 0 || position.x >= columns || position.y < 0 || position.y >= rows
    }
}
