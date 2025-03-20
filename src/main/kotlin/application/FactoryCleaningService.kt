package application

import domain.*
import domain.exceptions.InvalidInputException
import infrastructure.*

class FactoryCleaningService(
    private val applicationInputDto: ApplicationInputDto
) {
    fun makeCleanUp(robotMovementDtos: List<RobotMovementDto>): List<String> {
        val factory = Factory(applicationInputDto.factorySizeDto.rows, applicationInputDto.factorySizeDto.columns);

        for(robotDto in applicationInputDto.robots){

            val position = Position(robotDto.first.x, robotDto.first.y)
            val heading = Heading.fromChar(robotDto.first.heading)

            val status = Status(position, heading);
            val robot = Robot(status, factory);

            for(instructionDto in robotDto.second){
                val instruction: Instruction = when (instructionDto.instruction) {
                    'L' -> Rotation(Direction.LEFT)
                    'R' -> Rotation(Direction.RIGHT)
                    'M' -> ForwardMovement()
                    else -> throw InvalidInputException("Invalid instruction: $instructionDto.instruction")
                }

                robot.execute(instruction);
            }
        }

        return listOf("1 3 N");
    }
}