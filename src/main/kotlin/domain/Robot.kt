package domain

data class Robot(var status: Status, var factory: Factory) {

    init {
        status = factory.updateRobotStatus(this, status);
    }
    fun execute(instruction: Instruction) {
        val newStatus = instruction.execute(status)
        status = factory.updateRobotStatus(this, newStatus);
    }
}