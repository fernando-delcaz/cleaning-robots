import domain.Factory
import domain.Heading
import domain.Position
import domain.Robot
import domain.exceptions.OutsideOfTheFactoryBoundariesException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertNull

private const val FACTORY_FLOOR_ROWS = 5
private const val FACTORY_FLOOR_COLUMNS = 5

class FactoryShould {

    @Test
    fun raiseAnExceptionIfAskingOutsideTheGrid(){
         val factory = Factory(3,3);
         assertFailsWith<OutsideOfTheFactoryBoundariesException> { factory.whatsIn(Position(3, 4)) }
    }

    @Test
    fun returnNullIfAskingForAnEmptySlot(){
        val factory = Factory(3,3);
        assertNull(factory.whatsIn(Position(0,0)));
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
            factory.place(Robot(Position(positionX, positionY), Heading.NORTH));
        }
    }

    @Test
    fun beAbleToBePlacedInAnEmptySlotOnTheFloor() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 0)
        val myRobot = Robot(position, Heading.NORTH)

        factory.place(myRobot);

        val expectedRobot = factory.whatsIn(position);
        assertEquals(expectedRobot, myRobot, "Robot is not place where it should be")
    }
}
