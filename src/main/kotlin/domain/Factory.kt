package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException

class Factory(private val rows: Int, private val columns: Int) {

    private val factoryFloor: Array<Array<Robot?>> = Array(columns) { Array(rows) { null } }

    fun place(robot: Robot) {
        try {
            factoryFloor[robot.position.y][robot.position.x] = robot;
        } catch (exception: ArrayIndexOutOfBoundsException) {
            throw OutsideOfTheFactoryBoundariesException("Robot placed outside grid boundaries!")
        }
    }

    fun whatsIn(position: Position): Robot? {
        if (isOutOfTheFactoryBoundaries(position)) {
            throw OutsideOfTheFactoryBoundariesException("You can´t query a position outside the factory grid boundaries")
        }
        return factoryFloor[position.x][position.y]
    }

    private fun isOutOfTheFactoryBoundaries(position: Position) = position.x < 0 || position.x > rows || position.y < 0 || position.y > columns
}