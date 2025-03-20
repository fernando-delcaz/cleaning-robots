package domain

class ForwardMovement : Instruction() {
    override fun execute(currentStatus: Status): Status {
        return Status(Position(0,1), currentStatus.heading);
    }

}
