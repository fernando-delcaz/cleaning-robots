package domain

class Rotation(val rotateTo: Direction) : Instruction() {
    val rotateLeftMap: Map<Heading, Heading> = mapOf(
        Heading.NORTH to Heading.WEST,
        Heading.WEST to Heading.SOUTH,
        Heading.SOUTH to Heading.EAST,
        Heading.EAST to Heading.NORTH
    )

    val rotateRightMap: Map<Heading, Heading> = mapOf(
        Heading.NORTH to Heading.EAST,
        Heading.EAST to Heading.SOUTH,
        Heading.SOUTH to Heading.WEST,
        Heading.WEST to Heading.NORTH
    )
    override fun execute(robot: Robot): Status {
        val currentStatus = robot.status;
        when(rotateTo){
            Direction.LEFT -> return Status(currentStatus.position, rotateLeftMap[currentStatus.heading]!!);
            Direction.RIGHT -> return Status(currentStatus.position, rotateRightMap[currentStatus.heading]!!);
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Rotation) return false
        return rotateTo == other.rotateTo
    }

    override fun hashCode(): Int {
        return rotateTo.hashCode()
    }
}

enum class Direction {
    LEFT, RIGHT
}