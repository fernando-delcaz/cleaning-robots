package domain

data class Robot(var status: Status, var factory: Factory) {
    init {
        factory.place(this)
    }

    fun updateStatus(instruction: Instruction) {
        val newStatus = instruction.execute(status)
        status = factory.updateRobotStatus(this, newStatus);
    }
}