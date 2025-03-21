package unit

import domain.Instruction
import domain.exceptions.InvalidStatusException
import infrastructure.Dto.InstructionDto
import infrastructure.RobotInstructionParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertFailsWith

class RobotInstructionParserShould {

    @ParameterizedTest
    @CsvSource(
        "L, L",
        "RLMLMR, R, L, M, L, M, R, L, M, R"
    )
    fun parseValidInstructions(instructions: String, vararg expectedInstructions: Char) {
        val robotInstructionParser = RobotInstructionParser()
        val expectedResult = expectedInstructions.map { InstructionDto(it) }

        assertEquals(expectedResult, robotInstructionParser.parse(instructions))
    }

    @ParameterizedTest
    @CsvSource(
        "1",
        "A",
        "1 2 Z"
    )
    fun raiseAnExceptionOnInvalidInstruction(instructions: String) {
        val robotInstructionParser = RobotInstructionParser()
        assertFailsWith<InvalidStatusException> { robotInstructionParser.parse(instructions) }
    }

    @ParameterizedTest
    @CsvSource(
        "RLMLMR, R, L, M, L, M, R, L, M, R",
        "L, L",
        "M, M"
    )
    fun processValidMovements(instructions: String, vararg expectedInstructions: Char) {
        val robotInstructionParser = RobotInstructionParser()
        val expectedResult = expectedInstructions.map { InstructionDto(it) }

        assertEquals(expectedResult, robotInstructionParser.parse(instructions))
    }
    @ParameterizedTest
    @CsvSource(
        "'', ''"
    )
    fun returnEmptyListOnEmptyInstruction(instructions: String, vararg expectedInstructions: Char) {
        val robotInstructionParser = RobotInstructionParser()
        val expectedResult = expectedInstructions.map { InstructionDto(it) }

        assertEquals(expectedResult, robotInstructionParser.parse(instructions))
    }
}
