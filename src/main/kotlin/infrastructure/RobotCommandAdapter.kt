package infrastructure

interface RobotCommandAdapter {
    fun sendInstructionsToApplication(input: String)
}