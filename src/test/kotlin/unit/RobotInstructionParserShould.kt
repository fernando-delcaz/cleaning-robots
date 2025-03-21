package unit

import domain.Instruction
import domain.exceptions.InvalidStatusException
import infrastructure.Dto.InstructionDto
import infrastructure.Parsers.RobotInstructionParser
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith

class RobotInstructionParserShould {

    @Test
    fun parseOneValidInstruction() {
        val instructions = "L";
        val robotInstructionParser = RobotInstructionParser();

        val expectedResult = listOf(InstructionDto('L'));
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

    @Test
    fun processOneMixOfRotationsAndMovements() {
        val instructions = "RLMLMR";
        val robotInstructionParser = RobotInstructionParser();

        val expectedResult = listOf(
            InstructionDto('R'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('R'),
        )
        assertEquals(expectedResult, robotInstructionParser.parse(instructions))
    }

    @Test
    fun processAnotherMixOfRotationsAndMovements() {
        val instructions = "RLMLMR";
        val robotInstructionParser = RobotInstructionParser();

        val expectedResult = listOf(
            InstructionDto('R'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('R'),
        )
        assertEquals(expectedResult, robotInstructionParser.parse(instructions))
    }
}


