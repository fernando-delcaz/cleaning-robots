package unit

import domain.*
import domain.exceptions.OutsideOfTheFactoryBoundariesException
import domain.exceptions.TileAlreadyOccupiedException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.*

class FactoryShould {

    private val FACTORY_FLOOR_ROWS = 5
    private val FACTORY_FLOOR_COLUMNS = 5
    @Test
    fun raiseAnExceptionIfAskingOutsideTheGrid(){
         val factory = Factory(3, 3);
        assertFailsWith<OutsideOfTheFactoryBoundariesException> { factory.canIMoveTo(Position(3, 4)) }
    }

    @ParameterizedTest
    @CsvSource(
        "5, 6",
        "6, 5",
        "-1, 5",
        "5, -1",
    )
    fun raiseAnExceptionIfPlacedOutsideTheGrid(positionX: Int, positionY: Int) {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)

        assertFailsWith<OutsideOfTheFactoryBoundariesException>("Robot placed outside grid boundaries!") {
            Robot(Status(Position(positionX, positionY), Heading.NORTH), factory);
        }
    }

    @Test
    fun beAbleToPlaceOneRobotOnTheFloorOrigin() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 0)
        val myRobot = Robot(Status(position, Heading.NORTH), factory)

        val result = factory.moveRobot(myRobot, myRobot.status)
        assertEquals(result, myRobot.status, "Robot is not place where it should be")
    }

    @Test
    fun beAbleToPlaceOneRobotInAnEmptySlotInTheFloor() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 1)
        val myRobot = Robot(Status(position, Heading.NORTH), factory)

        val result = factory.moveRobot(myRobot, myRobot.status)
        assertEquals(result, myRobot.status, "Robot is not place where it should be")
    }


    @Test
    fun updateOneRobotPosition() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 0)

        val myRobot = Robot(Status(position, Heading.NORTH), factory)

        val instruction = ForwardMovement();
        myRobot.execute(instruction);

        assertEquals(Status(Position(0, 1), Heading.NORTH), myRobot.status, "Robot is not place where it should be")
        assertTrue { factory.canIMoveTo(position) }
    }

    @Test
    fun notUpdateTheRobotPositionIfItsGoingOutOfBounds() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 4)

        val myRobot = Robot(Status(position, Heading.NORTH), factory)

        val instruction = ForwardMovement();
        myRobot.execute(instruction)

        assertFalse { factory.canIMoveTo(position) }

        val result = factory.moveRobot(myRobot, myRobot.status)
        assertEquals(result, myRobot.status, "Robot is not place where it should be")
    }

    @Test
    fun notAllowOneRobotToLandOverAnother() {
        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 4)

        Robot(Status(position, Heading.NORTH), factory)

        assertFailsWith<TileAlreadyOccupiedException> { Robot(Status(position, Heading.NORTH), factory) }
    }
}