package unit

import domain.Heading
import domain.Position
import domain.Status
import kotlin.test.Test
import kotlin.test.assertEquals

class RobotStatusParserShould {

    @Test
    fun parseOneStatus() {
        var initialStatus = "1 2 N";
        var robotStatusParser = RobotStatusParser();

        val spectedStatus = Status(Position(1, 2), Heading.NORTH);
        assertEquals(spectedStatus, robotStatusParser.parse(initialStatus))
    }

    class RobotStatusParser {
        fun parse(initialStatus: String): Status {
            return Status(Position(1, 2), Heading.NORTH);
        }
    }
}