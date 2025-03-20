package infrastructure
import domain.exceptions.InvalidStatusException

private val validInstructions: Set<Char> = setOf('L', 'R', 'M')
class RobotInstructionParser {
    fun parse(instructions: String): List<InstructionDto> {
        return instructions.map { instructionChar ->
            if (instructionChar !in validInstructions) {
                throw InvalidStatusException("Invalid instruction: $instructionChar")
            }
            InstructionDto(instructionChar)
        }
    }
}