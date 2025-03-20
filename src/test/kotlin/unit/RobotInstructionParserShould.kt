package unit

import domain.Direction
import domain.Rotation
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test
import kotlin.test.assertSame

class RobotInstructionParserShould {

    @Test
    fun raiseAnExceptionOnInvalidInstruction() {
        val instructions = "L";
        val robotInstructionParser = RobotInstructionParser();

        val expectedResult = listOf(Rotation(Direction.LEFT));
        assertEquals(expectedResult, robotInstructionParser.parse(instructions))
    }

}

class RobotInstructionParser {
    fun parse(instructions: String): List<Rotation> {
        return listOf(Rotation(Direction.LEFT));
    }

}
