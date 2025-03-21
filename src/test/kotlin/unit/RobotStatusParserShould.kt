package unit

import domain.exceptions.InvalidStatusException
import infrastructure.RobotStatusParser
import infrastructure.Dto.StatusDto
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RobotStatusParserShould {

    @Test
    fun parseOneStatus() {
        var initialStatus = "1 2 N";
        var robotStatusParser = RobotStatusParser();

        val spectedStatus = StatusDto(1, 2, 'N');
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

    @Test
    fun raiseAnExceptionOnInvalidCoordinateY() {
        var initialStatus = "1 A N";
        var robotStatusParser = RobotStatusParser();

        assertFailsWith<InvalidStatusException> { robotStatusParser.parse(initialStatus)}
    }

    @Test
    fun raiseAnExceptionOnInvalidHeading() {
        var initialStatus = "1 1 L";
        var robotStatusParser = RobotStatusParser();

        assertFailsWith<InvalidStatusException> { robotStatusParser.parse(initialStatus)}
    }



}