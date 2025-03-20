package integration

import application.FactoryCleaningService
import domain.*
import infrastructure.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationShould {

    @Test
    fun handleOneRobotToCleanOneFactory() {
        val input = """5 5
                       1 2 N
                       LMLMLMLMM"""
        val application = Application();

        val expectedOutput = """1 3 N"""
        assertEquals(expectedOutput, application.executeCleanUp(input).first())
    }

    @Test
    fun handleOneRobotToCleanOneFactoryNotMoving() {
        val input = """5 5
                       1 2 N
                       L
                       """
        val application = Application();

        val expectedOutput = """1 2 W"""
        assertEquals(expectedOutput, application.executeCleanUp(input).first())
    }
}

class Application {
    fun executeCleanUp(input: String): List<String> {

        val inputParser = ApplicationInputParser();
        val applicationInputDto = inputParser.parse(input);

        val cleaningService = FactoryCleaningService(applicationInputDto);
        val result = cleaningService.makeCleanUp()

        return result;
    }

}
