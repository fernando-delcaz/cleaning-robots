package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException
import domain.exceptions.TileAlreadyOccupiedException

class Factory(rows: Int, columns: Int) {

    var factoryFloor = FactoryFloor(rows, columns)

    fun canIMoveTo(position: Position): Boolean {
        return !factoryFloor.isTileOccupied(position)
    }

    internal fun moveRobot(robot: Robot, desiredStatus: Status): Status {
        val initialRobotStatus = robot.status

        try {
            factoryFloor.clearTile(robot)
            robot.status = desiredStatus
            factoryFloor.updateTileContent(robot)

            return robot.status
        } catch (exception: OutsideOfTheFactoryBoundariesException) {

            println("Robot cannot go out of the factory grid boundaries!")
            return rollBack(robot, initialRobotStatus)

        } catch (exception: TileAlreadyOccupiedException) {

            println("Tile was already occupied! Skipping movement")
            return rollBack(robot, initialRobotStatus)
        }
    }

    private fun rollBack(robot: Robot, initialRobotStatus: Status): Status {
        robot.status = initialRobotStatus
        factoryFloor.updateTileContent(robot)
        return initialRobotStatus
    }

}


