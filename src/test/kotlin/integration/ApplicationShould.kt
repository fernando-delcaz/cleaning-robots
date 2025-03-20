package integration

import application.Application
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

    @Test
    fun handleTwoRobotsWithoutCollision() {
        val input = """5 5
                       1 2 N
                       LMLMLMLMM
                       3 3 E
                       MMRMMRMRRM"""
        val application = Application()

        val expectedOutput = listOf("1 3 N", "4 1 E")
        assertEquals(expectedOutput, application.executeCleanUp(input))
    }
}
