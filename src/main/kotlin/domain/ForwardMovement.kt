package domain

class ForwardMovement(val factory: Factory) : Instruction() {
    override fun execute(robot: Robot): Status {

        var currentStatus = robot.status;
        var nextStatus: Status = Status(currentStatus.position, currentStatus.heading);
        when (currentStatus.heading) {
            Heading.NORTH -> nextStatus =
                Status(Position(currentStatus.position.x, currentStatus.position.y + 1), currentStatus.heading);
            Heading.SOUTH -> nextStatus =
                Status(Position(currentStatus.position.x, currentStatus.position.y - 1), currentStatus.heading);
            Heading.EAST -> nextStatus =
                Status(Position(currentStatus.position.x + 1, currentStatus.position.y), currentStatus.heading);
            Heading.WEST -> nextStatus =
                Status(Position(currentStatus.position.x - 1, currentStatus.position.y), currentStatus.heading);
        }

        if (!factory.canIMoveTo(nextStatus.position))
            return currentStatus;

        return factory.moveRobot(robot, nextStatus)
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
