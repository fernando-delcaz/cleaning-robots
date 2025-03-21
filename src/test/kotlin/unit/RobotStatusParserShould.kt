package unit

import domain.exceptions.InvalidStatusException
import infrastructure.Parsers.RobotStatusParser
import infrastructure.Dto.StatusDto
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class RobotStatusParserShould {

    @ParameterizedTest
    @CsvSource(
        "1 2 N, 1, 2, N",
        "2 3 E, 2, 3, E",
        "4 4 S, 4, 4, S"
    )
    fun parseValidStatuses(initialStatus: String, x: Int, y: Int, heading: Char) {
        val robotStatusParser = RobotStatusParser()
        val expectedStatus = StatusDto(x, y, heading)
        assertEquals(expectedStatus, robotStatusParser.parse(initialStatus))
    }

    @ParameterizedTest
    @CsvSource(
        "'', InvalidStatusException",
        "A 2 N, InvalidStatusException",
        "1 A N, InvalidStatusException",
        "1 1 L, InvalidStatusException"
    )
    fun raiseAnExceptionOnInvalidStatus(initialStatus: String, exceptionType: String) {
        val robotStatusParser = RobotStatusParser()
        assertFailsWith(InvalidStatusException::class) { robotStatusParser.parse(initialStatus) }
    }
}
