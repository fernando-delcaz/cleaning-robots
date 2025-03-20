package domain

class ForwardMovement : Instruction() {
    override fun execute(currentStatus: Status): Status {
        when(currentStatus.heading){
            Heading.NORTH -> return Status(Position(currentStatus.position.x, currentStatus.position.y + 1), currentStatus.heading);
            Heading.SOUTH -> return Status(Position(currentStatus.position.x, currentStatus.position.y - 1), currentStatus.heading);
            Heading.EAST -> return Status(Position(currentStatus.position.x + 1, currentStatus.position.y), currentStatus.heading);
            Heading.WEST -> return Status(Position(currentStatus.position.x - 1, currentStatus.position.y), currentStatus.heading);
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ForwardMovement) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}
