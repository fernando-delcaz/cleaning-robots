package unit

import domain.Heading
import domain.InvalidStatusException
import domain.Position
import domain.Status
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RobotStatusParserShould {

    @Test
    fun parseOneStatus() {
        var initialStatus = "1 2 N";
        var robotStatusParser = RobotStatusParser();

        val spectedStatus = Status(Position(1, 2), Heading.NORTH);
        assertEquals(spectedStatus, robotStatusParser.parse(initialStatus))
    }

    @Test
    fun raiseAnExceptionOnEmptyStatus() {
        var initialStatus = "";
        var robotStatusParser = RobotStatusParser();

        assertFailsWith<InvalidStatusException> { robotStatusParser.parse(initialStatus)}
    }

    @Test
    fun raiseAnExceptionOnInvalidCoordinateX() {
        var initialStatus = "A 2 N";
        var robotStatusParser = RobotStatusParser();

        assertFailsWith<InvalidStatusException> { robotStatusParser.parse(initialStatus)}
    }

    class RobotStatusParser {
        fun parse(initialStatus: String): Status {
            val statusParts = initialStatus.split(" ");
            if (statusParts.size != 3) {
                throw InvalidStatusException("Invalid status format")
            }

            val x = statusParts[0].toIntOrNull()

            if(x == null){
                throw InvalidStatusException("Invalid status format")
            }

            return Status(Position(1, 2), Heading.NORTH);
        }
    }
}