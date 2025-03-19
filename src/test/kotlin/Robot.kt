import domain.*
import domain.exceptions.OutsideOfTheFactoryBoundariesException
import kotlin.test.Test
import kotlin.test.assertFailsWith

private const val FACTORY_FLOOR_ROWS = 5
private const val FACTORY_FLOOR_COLUMNS = 5

class RobotShould {
    @Test
    fun raiseAnExceptionIfPlacedOutsideTheGrid() {

        val factory = Factory(FACTORY_FLOOR_ROWS, FACTORY_FLOOR_COLUMNS)

        assertFailsWith<OutsideOfTheFactoryBoundariesException>("Robot placed outside grid boundaries!") {
            factory.place(Robot(Position(5, 6), Heading.NORTH));
        }
    }
}