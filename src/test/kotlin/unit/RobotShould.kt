package unit

import domain.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals

class RobotShould {

    private lateinit var factory: Factory
    private lateinit var robot: Robot
    @BeforeEach
    fun setup() {
        factory = Factory(5, 5)
        robot = Robot(Status(Position(0, 0), Heading.NORTH), factory)
    }

    @Test
    fun beAssignedToOneFactory(){
        assertEquals(robot, factory.whatsIn(robot.status.position));
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
        robot = Robot(Status(Position(0, 0), initialHeading), factory)
        val instruction = Rotation(Direction.valueOf(rotationDirection))
        val expectedStatus = Status(Position(0, 0), expectedHeading)

        robot.updateStatus(instruction)

        assertEquals(robot.status, expectedStatus)
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

        val instruction = ForwardMovement();
        robot.updateStatus(instruction)

        assertEquals(robot.status, expectedStatus)
    }

    @ParameterizedTest
    @CsvSource(
        "NORTH, 2, 4",
        "EAST, 4, 2",
        "SOUTH, 2, 0",
        "WEST, 0, 2",
    )
    fun moveUpForwardTwoSteps(initialHeading: Heading, expectedPositionX: Int, expectedPositionY: Int){
        robot = Robot(Status(Position(2, 2), initialHeading), factory)
        val expectedStatus = Status(Position(expectedPositionX, expectedPositionY), initialHeading)

        val instruction = ForwardMovement();
        robot.updateStatus(instruction)
        robot.updateStatus(instruction)

        assertEquals(robot.status, expectedStatus)
    }
}


