package integration

import application.Application
import domain.exceptions.OutsideOfTheFactoryBoundariesException
import infrastructure.Dto.RobotOutputStatusDto
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ApplicationShould {

    @Test
    fun handleOneRobotToCleanOneFactoryJustMovingOnce() {
        val input = """5 5
                       1 2 N
                       M"""
        val application = Application()

        val expectedOutput: List<RobotOutputStatusDto> = listOf(RobotOutputStatusDto("""1 3 N"""))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }

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

        val expectedOutput = listOf(RobotOutputStatusDto("1 2 N"), RobotOutputStatusDto("1 1 N"))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }

    @Test
    fun handleOneRobotHittingTheWall() {
        val input = """5 5
                   1 1 S
                   M"""
        val application = Application()

        val expectedOutput = listOf(RobotOutputStatusDto("1 1 S"))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }

    @Test
    fun handleRobotsHittingTheWall() {
        val input = """5 5
                   1 1 S
                   M
                   5 5 N
                   M"""
        val application = Application()

        val expectedOutput = listOf(RobotOutputStatusDto("1 1 S"), RobotOutputStatusDto("5 5 N"))
        assertEquals<Any>(expectedOutput, application.executeCleanUp(input))
    }
}
