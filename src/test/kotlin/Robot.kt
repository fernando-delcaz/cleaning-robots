import domain.Factory
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertFailsWith

class RobotShould {
    @ParameterizedTest
    @CsvSource(
        "6, 6",
        "-1, 2",
        "10, -1"
    )
    fun raiseAnExceptionIfPlacedOutsideTheGrid(robotPositionX: Int, robotPositionY: Int) {
        val factory = Factory(5,5)

        assertFailsWith<ArrayIndexOutOfBoundsException>("Robot placed outside grid boundaries!") {
            factory.place(Robot(Heading.NORTH), robotPositionX, robotPositionY);
        }
    }

}