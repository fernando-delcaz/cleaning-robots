package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException

class Factory(private val rows: Int, private val columns: Int) {

    private val factoryFloor: Array<Array<Robot?>> = Array(columns) { Array(rows) { null } }

    internal fun place(robot: Robot) {
        try {
            factoryFloor[robot.status.position.x][robot.status.position.y] = robot
        } catch (exception: ArrayIndexOutOfBoundsException) {
            throw OutsideOfTheFactoryBoundariesException("Robot placed outside grid boundaries!")
        }
    }

    private fun emptyTile(status: Status) {
        factoryFloor[status.position.y][status.position.x] = null
    }

    fun whatsIn(position: Position): Robot? {
        if (isOutOfTheFactoryBoundaries(position)) {
            throw OutsideOfTheFactoryBoundariesException("You can´t query a position outside the factory grid boundaries")
        }
        return factoryFloor[position.x][position.y]
    }

    internal fun updateRobotStatus(robot: Robot, desiredStatus: Status): Status {
        try {
            val updatedRobot = robot.copy(status = desiredStatus)
            place(updatedRobot)
            emptyTile(robot.status)
            return updatedRobot.status
        } catch (exception: OutsideOfTheFactoryBoundariesException) {
            print("Robot cannot go out of the factory grid boundaries!")
            return robot.status
        }
    }

    private fun isOutOfTheFactoryBoundaries(position: Position) =
        position.x < 0 || position.x > rows || position.y < 0 || position.y > columns
}