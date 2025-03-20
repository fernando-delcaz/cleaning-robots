package unit

import infrastructure.FactorySizeDto
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.Test

class FactoryInputParserShould {

    private val factoryInputParser = FactoryInputParser()
    @Test
    fun parseFactorySizeFromFirstLine() {
        val input = "5 5"
        val expected = FactorySizeDto(5, 5)
        assertEquals(expected, factoryInputParser.parse(input))
    }
}

class FactoryInputParser {
    fun parse(input: String): FactorySizeDto {
        val parts = input.split(" ")
        val rows = parts[0].toInt()
        val columns = parts[1].toInt()
        return FactorySizeDto(rows, columns)
    }
}
