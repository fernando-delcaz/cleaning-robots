package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException

class Factory(private val rows: Int, columns : Int) {

    fun place(robot: Robot) {
        throw OutsideOfTheFactoryBoundariesException("Robot placed outside grid boundaries!")
    }
}