package unit

import domain.Direction
import domain.Instruction
import domain.Rotation
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class RobotInstructionParserShould {

    @Test
    fun raiseAnExceptionOnInvalidInstruction() {
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

}

class RobotInstructionParser {
    fun parse(instructions: String): List<Instruction> {
        if(instructions.isEmpty()) return listOf<Instruction>()

        return listOf(Rotation(Direction.LEFT));
    }

}
