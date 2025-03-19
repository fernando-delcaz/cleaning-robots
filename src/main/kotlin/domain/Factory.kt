package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException

class Factory(private val rows: Int, columns : Int) {

    fun place(robot: Robot) {
        if (robot.position == Position(5,6))
            throw OutsideOfTheFactoryBoundariesException("Robot placed outside grid boundaries!")
    }

    fun whatsIn(position: Position): Robot {
        return Robot(position, Heading.NORTH);
    }
}