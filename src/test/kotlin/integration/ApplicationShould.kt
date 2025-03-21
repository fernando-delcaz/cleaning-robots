package integration

import application.Application
import domain.exceptions.OutsideOfTheFactoryBoundariesException
import infrastructure.Dto.RobotOutputStatusDto
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ApplicationShould {

    @Test
    fun handleOneRobotToCleanOneFactory() {
        val input = """5 5
                       1 2 N
                       LMLMLMLMM"""
        val application = Application()

        val expectedOutput: List<RobotOutputStatusDto> = listOf(RobotOutputStatusDto("""2 3 N"""))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }

    @Test
    fun handleAnotherRobotToCleanOneFactory() {
        val input = """5 5
                       3 3 E
                       MMRMMRMRRM"""
        val application = Application()

        val expectedOutput: List<RobotOutputStatusDto> = listOf(RobotOutputStatusDto("""5 1 E"""))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }

    @Test
    fun handleOneRobotToCleanOneFactoryNotMoving() {
        val input = """5 5
                       1 2 N
                       L
                       """
        val application = Application()

        val expectedOutput = listOf(RobotOutputStatusDto("""1 2 W"""))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }

    @Test
    fun handleTwoRobotsWithoutCollision() {
        val input = """5 5
                       1 2 N
                       LMLMLMLMM
                       3 3 E
                       MMRMMRMRRM"""
        val application = Application()

        val expectedOutput = listOf(RobotOutputStatusDto("2 3 N"), RobotOutputStatusDto("5 1 E"))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }

    @Test
    fun raiseAnExceptionWhenTheInputIsInvalid() {
        val input = """5 5
                   0 0 N
                   M
                   0 0 N
                   M"""
        val application = Application()
        assertFailsWith<OutsideOfTheFactoryBoundariesException> { application.executeCleanUp(input) }
    }

    @Test
    fun handleTwoRobotsWithCollisionInFirstMove() {
        val input = """5 5
                   1 1 N
                   M
                   1 1 N
                   M"""
        val application = Application()

        val expectedOutput = listOf(RobotOutputStatusDto("0 1 N"), RobotOutputStatusDto("0 0 N"))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }

    @Test
    fun handleRobotsHittingTheWall() {
        val input = """5 5
                   0 0 S
                   M
                   4 4 N
                   M"""
        val application = Application()

        val expectedOutput = listOf(RobotOutputStatusDto("0 0 S"), RobotOutputStatusDto("4 4 N"))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }
}
