package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException

class Factory(private val rows: Int, columns: Int) {

    private val factoryFloor: Array<Array<Robot?>> = Array(columns) { Array(rows) { null } }

    fun place(robot: Robot) {
        try {
            factoryFloor[robot.position.y][robot.position.x] = robot;
        } catch (exception: ArrayIndexOutOfBoundsException) {
            throw OutsideOfTheFactoryBoundariesException("Robot placed outside grid boundaries!")
        }
    }

    fun whatsIn(position: Position): Robot {
        return Robot(position, Heading.NORTH);
    }
}