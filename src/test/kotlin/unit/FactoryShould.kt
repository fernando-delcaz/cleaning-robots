package unit

import domain.*
import domain.exceptions.OutsideOfTheFactoryBoundariesException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

class FactoryShould {

    private val FACTORY_FLOOR_ROWS = 5
    private val FACTORY_FLOOR_COLUMNS = 5
    @Test
    fun raiseAnExceptionIfAskingOutsideTheGrid(){
         val factory = Factory(3, 3);
        assertFailsWith<OutsideOfTheFactoryBoundariesException> { factory.whatsIn(Position(3, 4)) }
    }

    @Test
    fun returnNullIfAskingForAnEmptySlot(){
        val factory = Factory(3, 3);
        assertNull(factory.whatsIn(Position(0, 0)));
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
            factory.place(Robot(Status(Position(positionX, positionY), Heading.NORTH), factory));
        }
    }

    @Test
    fun beAbleToPlaceOneRobotInAnEmptySlotInTheFloor() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 0)
        val myRobot = Robot(Status(position, Heading.NORTH), factory)

        factory.place(myRobot);

        val expectedRobot = factory.whatsIn(position);
        assertEquals(expectedRobot, myRobot, "Robot is not place where it should be")
    }

    @Test
    fun updateOneRobotPosition() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 0)

        val myRobot = Robot(Status(position, Heading.NORTH), factory)

        val instruction = ForwardMovement();
        myRobot.updateStatus(instruction)

        val expectedEmptySpace = factory.whatsIn(position);
        assertNull(expectedEmptySpace, "Robot it´s duplicated or did not move");
        assertEquals(factory.whatsIn(Position(0, 1)), myRobot, "Robot is not placed where it should be")
    }
}