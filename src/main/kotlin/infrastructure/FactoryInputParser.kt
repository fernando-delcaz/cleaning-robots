package infrastructure

import domain.exceptions.InvalidInputException
import infrastructure.Dto.FactorySizeDto

class FactoryInputParser {
    fun parse(input: String): FactorySizeDto {
        val parts = input.split(" ")
        if (parts.size != 2) {
            throw InvalidInputException("Invalid input, should contain two values: rows and columns")
        }
        val rows = parts[0].toIntOrNull()
        val columns = parts[1].toIntOrNull()
        if (rows == null || columns == null) {
            throw InvalidInputException("Values must be numbers")
        }

        if (rows <= 0 || columns <= 0) {
            throw InvalidInputException("Factory should have at least 1 row and 1 column")
        }

        return FactorySizeDto(rows, columns)

    }
}