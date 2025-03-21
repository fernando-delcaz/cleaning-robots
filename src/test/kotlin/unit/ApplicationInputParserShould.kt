package unit

import infrastructure.Dto.ApplicationInputDto
import infrastructure.Dto.FactorySizeDto
import infrastructure.Dto.InstructionDto
import infrastructure.Dto.StatusDto
import infrastructure.Parsers.ApplicationInputParser
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationInputParserShould {
    @Test
    fun parseTheApplicationInputForOneRobot() {
        val input = """5 5
                       1 2 N
                       LMLMLMLMM"""

        val factorySizeDto = FactorySizeDto(5, 5)
        val robotInitialStatusDto = StatusDto(1, 2, 'N')
        val robotInputInstructionsDto = listOf(
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('M')

        )

        val expectedOutput = ApplicationInputDto(
            factorySizeDto,
            listOf(Pair(robotInitialStatusDto, robotInputInstructionsDto))
        )

        val applicationInputParser = ApplicationInputParser()
        assertEquals(expectedOutput, applicationInputParser.parse(input))
    }

    @Test
    fun parseTheApplicationInputForTwoRobots() {
        val factorySizeDto = FactorySizeDto(5, 5)
        val robotOneInitialStatusDto = StatusDto(1, 2, 'N')
        val robotOneInputInstructionsDto = listOf(
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('L'),
            InstructionDto('M'),
            InstructionDto('M')
        )

        val robotTwoInitialStatusDto = StatusDto(3, 3, 'E')
        val robotTwoInputInstructionsDto = listOf(
            InstructionDto('M'),
            InstructionDto('M'),
            InstructionDto('R'),
            InstructionDto('M'),
            InstructionDto('M'),
            InstructionDto('R'),
            InstructionDto('M'),
            InstructionDto('R'),
            InstructionDto('R'),
            InstructionDto('M')
        )

        val expectedOutput = ApplicationInputDto(
            factorySizeDto,
            listOf(
                Pair(robotOneInitialStatusDto, robotOneInputInstructionsDto),
                Pair(robotTwoInitialStatusDto, robotTwoInputInstructionsDto)
            )
        )
        val applicationInputParser = ApplicationInputParser()
        val input = """5 5
                       1 2 N
                       LMLMLMLMM
                       3 3 E
                       MMRMMRMRRM"""

        assertEquals(expectedOutput, applicationInputParser.parse(input))
    }
}




