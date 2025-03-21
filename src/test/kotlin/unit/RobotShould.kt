package unit

import domain.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class RobotShould {

    private lateinit var factory: Factory
    private lateinit var robot: Robot
    @BeforeEach
    fun setup() {
        factory = Factory(5, 5)
    }

    @Test
    fun beAssignedToOneFactory(){
        robot = Robot(Status(Position(1, 1), Heading.NORTH), factory)
//        assertFalse { factory.canIMoveTo(robot.status.position) }
        assertEquals(robot.factory, factory);
    }

    @ParameterizedTest
    @CsvSource(
        "NORTH, RIGHT, EAST",
        "EAST, RIGHT, SOUTH",
        "SOUTH, RIGHT, WEST",
        "WEST, RIGHT, NORTH",
        "NORTH, LEFT, WEST",
        "WEST, LEFT, SOUTH",
        "SOUTH, LEFT, EAST",
        "EAST, LEFT, NORTH",
    )
    fun rotateTest(initialHeading: Heading, rotationDirection: String, expectedHeading: Heading) {
        val instruction = Rotation(Direction.valueOf(rotationDirection))
        val expectedStatus = Status(Position(1, 1), expectedHeading)

        robot = Robot(Status(Position(1, 1), initialHeading), factory)
        robot.execute(instruction)

        assertEquals(expectedStatus, robot.status)
    }

    @ParameterizedTest
    @CsvSource(
        "NORTH, 2, 3",
        "EAST, 3, 2",
        "SOUTH, 2, 1",
        "WEST, 1, 2",
    )
    fun moveUpForwardOneStep(initialHeading: Heading, expectedPositionX: Int, expectedPositionY: Int){
        robot = Robot(Status(Position(2, 2), initialHeading), factory)
        val expectedStatus = Status(Position(expectedPositionX, expectedPositionY), initialHeading)

        val instruction = ForwardMovement(factory);
        robot.execute(instruction)

        assertEquals(robot.status, expectedStatus)
    }

    @ParameterizedTest
    @CsvSource(
        "NORTH, 2, 4",
        "EAST, 4, 2",
        "SOUTH, 2, 1",
        "WEST, 1, 2",
    )
    fun moveUpForwardTwoSteps(initialHeading: Heading, expectedPositionX: Int, expectedPositionY: Int){
        robot = Robot(Status(Position(2, 2), initialHeading), factory)
        val expectedStatus = Status(Position(expectedPositionX, expectedPositionY), initialHeading)

        val instruction = ForwardMovement(factory);
        robot.execute(instruction)
        robot.execute(instruction)

        assertEquals(robot.status, expectedStatus)
    }
}


