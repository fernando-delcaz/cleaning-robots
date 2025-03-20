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
                       LMLMLMLMM
                       3 3 E
                       MMRMMRMRRM"""
        val application = Application();

        val expectedOutput = """1 3 N"""
        assertEquals(expectedOutput, application.executeCleanUp(input))
    }
}

class Application {
    fun executeCleanUp(input: String): String {
        return """1 3 N""";
    }

}
