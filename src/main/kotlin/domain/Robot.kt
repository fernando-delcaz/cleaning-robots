package domain

data class Robot(var status: Status, var factory: Factory) {

    init {
        status = factory.moveRobot(this, status);
    }
    fun execute(instruction: Instruction) {
        val newStatus = instruction.execute(status)
        status = factory.moveRobot(this, newStatus);
    }
}