package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException
import domain.exceptions.TileAlreadyOccupiedException

class Factory(private val rows: Int, private val columns: Int) {

    private val factoryFloor: Array<Array<Robot?>> = Array(columns) { Array(rows) { null } }

    private fun emptyTile(status: Status) {
        factoryFloor[status.position.x][status.position.y] = null
    }

    fun whatsIn(position: Position): Robot? {
        if (isOutOfTheFactoryBoundaries(position)) {
            throw OutsideOfTheFactoryBoundariesException("You can´t query a position outside the factory grid boundaries")
        }
        return factoryFloor[position.x][position.y]
    }

    internal fun updateRobotStatus(robot: Robot, desiredStatus: Status): Status {
        val initialRobotStatus = robot.status;
        val currentTileContent = whatsIn(robot.status.position);

        if (currentTileContent != null && currentTileContent !== robot) {
            throw TileAlreadyOccupiedException("A robot cannot land over another!")
        }

        return updateFactoryFloorStatus(robot, initialRobotStatus, desiredStatus)
    }

    private fun updateFactoryFloorStatus(
        robot: Robot,
        initialRobotStatus: Status,
        desiredStatus: Status
    ): Status {
        try {
            emptyTile(robot.status)
            robot.status = desiredStatus
            updateTileContent(robot)
            return robot.status
        } catch (exception: OutsideOfTheFactoryBoundariesException) {
            print("Robot cannot go out of the factory grid boundaries!")
            robot.status = initialRobotStatus
            updateTileContent(robot);
            return initialRobotStatus
        }
    }

    private fun updateTileContent(robot: Robot) {
        try {
            if(whatsIn(robot.status.position) != null){
                throw TileAlreadyOccupiedException("A robot cannot land over another!")
            }

            factoryFloor[robot.status.position.x][robot.status.position.y] = robot
        } catch (exception: ArrayIndexOutOfBoundsException) {
            throw OutsideOfTheFactoryBoundariesException("Robot placed outside grid boundaries!")
        }
    }

    private fun isOutOfTheFactoryBoundaries(position: Position) =
        position.x < 0 || position.x > rows || position.y < 0 || position.y > columns
}