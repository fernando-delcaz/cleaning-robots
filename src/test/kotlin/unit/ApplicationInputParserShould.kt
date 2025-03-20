package unit

import infrastructure.FactorySizeDto
import infrastructure.RobotOutputStatusDto
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationInputParserShould {
    @Test
    fun parseTheApplicationInputForOneRobot() {
        val input = """5 5
                       1 2 N
                       LMLMLMLMM"""

        val factorySizeDto = FactorySizeDto(5, 5);
        val robotInitialStatusDto  = "1 2 N";
        val robotInputInstructionsDto = "LMLMLMLMM";

        val expectedOutput = ApplicationInputDto(factorySizeDto, listOf(robotInitialStatusDto), listOf(robotInputInstructionsDto));
        val applicationInputParser = ApplicationInputParser();

        assertEquals(expectedOutput, applicationInputParser.parse(input));
    }

    @Test
    fun parseTheApplicationInputForTwoRobots() {
        val input = """5 5
                       1 2 N
                       LMLMLMLMM
                       3 3 E
                       MMRMMRMRRM
                       """

        val factorySizeDto = FactorySizeDto(5, 5);
        val robotOneInitialStatusDto  = "1 2 N";
        val robotOneInputInstructionsDto = "LMLMLMLMM";
        val robotTwoInitialStatusDto  = "3 3 E";
        val robotTwoInputInstructionsDto = "MMRMMRMRRM";

        val expectedOutput = ApplicationInputDto(factorySizeDto, listOf(robotOneInitialStatusDto, robotTwoInitialStatusDto), listOf(robotOneInputInstructionsDto, robotTwoInputInstructionsDto));
        val applicationInputParser = ApplicationInputParser();

        assertEquals(expectedOutput, applicationInputParser.parse(input));
    }
}

class ApplicationInputParser {
    fun parse(input: String): ApplicationInputDto {
        val inputParts = input.split(" ");
        val factorySizeDto = FactorySizeDto(1, 1)
        val robotInitialStatusDto  = "1 2 N";
        val robotInputInstructionsDto = "LMLMLMLMM";

        return ApplicationInputDto(factorySizeDto, listOf(robotInitialStatusDto), listOf(robotInputInstructionsDto));
    }
}

class Application {
    fun clean(input: String): RobotOutputStatusDto {
        return RobotOutputStatusDto("1 3 N");
    }

}

data class ApplicationInputDto(val factorySizeDto: FactorySizeDto, val robotInitialStatusDto: List<String>, val robotInputInstructionsDto: List<String>) {

}
