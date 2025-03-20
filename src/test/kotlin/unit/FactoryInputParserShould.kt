package unit

import domain.exceptions.InvalidInputException
import infrastructure.FactorySizeDto
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
    fun throwExceptionForMissingColumns() {
        val input = "5"
        assertFailsWith<InvalidInputException> {
            factoryInputParser.parse(input)
        }
    }
}

class FactoryInputParser {
    fun parse(input: String): FactorySizeDto {
        val parts = input.split(" ")
        if (parts.size != 2) {
            throw InvalidInputException("Invalid input, should contain two values: rows and columns")
        }
        val rows = parts[0].toInt()
        val columns = parts[1].toInt()
        return FactorySizeDto(rows, columns)

    }
}
