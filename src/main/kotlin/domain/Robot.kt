package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException

data class Robot(var status: Status, var factory: Factory) {

    init {
        require(factory.isInsideTheFactory(status.position) ) {
            throw OutsideOfTheFactoryBoundariesException("You can´t create a robot outside of the factory")
        }
    }

    init {
        status = factory.moveRobot(this, status);
    }
    fun execute(instruction: Instruction) {
        val newStatus = instruction.execute(this)
        status = factory.moveRobot(this, newStatus);
    }
}