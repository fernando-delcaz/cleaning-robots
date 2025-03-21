package infrastructure.Parsers
import domain.exceptions.InvalidStatusException
import infrastructure.Dto.InstructionDto

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