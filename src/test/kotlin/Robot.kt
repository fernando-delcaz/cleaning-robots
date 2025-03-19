import domain.*
import domain.exceptions.OutsideOfTheFactoryBoundariesException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private const val FACTORY_FLOOR_ROWS = 5
private const val FACTORY_FLOOR_COLUMNS = 5

class RobotShould {

    @ParameterizedTest
    @CsvSource(
        "5, 6",
        "6, 5",
    )
    fun raiseAnExceptionIfPlacedOutsideTheGrid(positionX: Int, positionY: Int) {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)

        assertFailsWith<OutsideOfTheFactoryBoundariesException>("Robot placed outside grid boundaries!") {
            factory.place(Robot(Position(positionX, positionY), Heading.NORTH));
        }
    }

    @Test
    fun beAbleToBePlacedOnOneTile() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)
        val position = Position(0, 0)
        var myRobot = Robot(position, Heading.NORTH)

        factory.place(myRobot);

        var expectedRobot = factory.whatsIn(position);
        assertEquals(expectedRobot, myRobot, "Robot is not place where it should be")
    }
}