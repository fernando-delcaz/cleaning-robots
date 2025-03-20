package domain

class Rotation(val rotateTo: Direction) : Instruction() {
    override fun execute(currentStatus: Status): Status {
        when(rotateTo){
            Direction.LEFT -> return Status(currentStatus.position, Heading.EAST);
            Direction.RIGHT -> return Status(currentStatus.position, Heading.EAST);
        }
    }

}

enum class Direction {
    LEFT, RIGHT
}