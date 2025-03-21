package unit

import domain.exceptions.InvalidInputException
import infrastructure.Parsers.FactoryInputParser
import infrastructure.Dto.FactorySizeDto
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test
import kotlin.test.assertFailsWith

class FactoryInputParserShould {

    private val factoryInputParser = FactoryInputParser()

    @Test
    fun parseFactorySizeFromFirstLine() {
        val input = "5 5"
        val expected = FactorySizeDto(5, 5)
        assertEquals(expected, factoryInputParser.parse(input))
    }

    @Test
    fun raiseExceptionForMissingColumns() {
        val input = "5"
        assertFailsWith<InvalidInputException> {
            factoryInputParser.parse(input)
        }
    }

    @Test
    fun raiseExceptionForInvalidInputWithNonIntegerValues() {
        val input = "5 a"
        assertFailsWith<InvalidInputException> {
            factoryInputParser.parse(input)
        }
    }

    @Test
    fun throwExceptionForNegativeValues() {
        val input = "-5 5"
        assertFailsWith<InvalidInputException> {
            factoryInputParser.parse(input)
        }
    }
}
