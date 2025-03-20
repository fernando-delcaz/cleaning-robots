package domain

sealed class Instruction {
    abstract fun execute(currentStatus: Status): Status
}