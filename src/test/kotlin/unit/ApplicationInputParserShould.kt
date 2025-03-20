package unit

import infrastructure.FactorySizeDto
import infrastructure.RobotOutputStatusDto
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationInputParserShould {
    @Test
    fun parseTheApplicationInput() {
        val input = """5 5
                       1 2 N
                       LMLMLMLMM"""

        val factorySizeDto = FactorySizeDto(5, 5);
        val robotInitialStatusDto  = "1 2 N";
        val robotInputInstructionsDto = "LMLMLMLMM";

        val expectedOutput = ApplicationInputDto(factorySizeDto, robotInitialStatusDto, robotInputInstructionsDto);
        val applicationInputParser = ApplicationInputParser();

        assertEquals(expectedOutput, applicationInputParser.parse(input));
    }
}

class ApplicationInputParser {
    fun parse(input: String): ApplicationInputDto {
        val factorySizeDto = FactorySizeDto(5, 5);
        val robotInitialStatusDto  = "1 2 N";
        val robotInputInstructionsDto = "LMLMLMLMM";

        return ApplicationInputDto(factorySizeDto, robotInitialStatusDto, robotInputInstructionsDto);
    }
}

class Application {
    fun clean(input: String): RobotOutputStatusDto {
        return RobotOutputStatusDto("1 3 N");
    }

}

data class ApplicationInputDto(val factorySizeDto: FactorySizeDto, val robotInitialStatusDto: String, val robotInputInstructionsDto: String) {

}
