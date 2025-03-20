package unit

import domain.Direction
import domain.Instruction
import domain.Rotation
import domain.exceptions.InvalidStatusException
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith

class RobotInstructionParserShould {

    @Test
    fun parseOneValidInstruction() {
        val instructions = "L";
        val robotInstructionParser = RobotInstructionParser();

        val expectedResult = listOf(Rotation(Direction.LEFT));
        assertEquals(expectedResult, robotInstructionParser.parse(instructions))
    }

    @Test
    fun returnEmptyListOnEmptyInstruction() {
        val instructions = "";
        val robotInstructionParser = RobotInstructionParser();

        val expectedResult = listOf<Instruction>();
        assertEquals(expectedResult, robotInstructionParser.parse(instructions))
    }

    @Test
    fun raiseAnExceptionOnInvalidInstruction() {
        val instructions = "1";
        val robotInstructionParser = RobotInstructionParser();

        assertFailsWith<InvalidStatusException>{robotInstructionParser.parse(instructions)};
    }
}

class RobotInstructionParser {
    fun parse(instructions: String): List<Instruction> {
        return instructions.map {
            when (it) {
                'L' -> Rotation(Direction.LEFT)
                'R' -> Rotation(Direction.RIGHT)
                else -> throw InvalidStatusException("Invalid instruction")
            }
        }
    }

}
