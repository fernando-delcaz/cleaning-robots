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
    override fun execute(currentStatus: Status, ): Status {
        when(rotateTo){
            Direction.LEFT -> return Status(currentStatus.position, rotateLeftMap[currentStatus.heading]!!);
            Direction.RIGHT -> return Status(currentStatus.position, rotateRightMap[currentStatus.heading]!!);
        }
    }
}

enum class Direction {
    LEFT, RIGHT
}