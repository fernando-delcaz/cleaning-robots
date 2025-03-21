package domain

sealed class Instruction {
    abstract fun execute(robot: Robot): Status
}