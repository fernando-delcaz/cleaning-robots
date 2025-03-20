package infrastructure

import domain.Heading
import domain.exceptions.InvalidStatusException
import domain.Position
import domain.Status
import java.util.*

class RobotStatusParser {
    fun parse(initialStatus: String): StatusDto {
        val statusParts = initialStatus.split(" ");
        if (statusParts.size != 3) {
            throw InvalidStatusException("Invalid status format")
        }

        val x = statusParts[0].toIntOrNull()
        val y = statusParts[1].toIntOrNull()

        if(x == null || y == null){
            throw InvalidStatusException("Invalid coordinates")
        }

        val heading = Heading.values().find { it.char == statusParts[2].uppercase(Locale.getDefault())[0] }

        if (heading == null) {
            throw InvalidStatusException("Invalid heading")
        }

        return StatusDto(x,y, statusParts[2].first().uppercaseChar());
    }
}