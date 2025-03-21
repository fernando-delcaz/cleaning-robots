package application

import domain.*
import domain.exceptions.InvalidInputException
import infrastructure.Dto.ApplicationInputDto
import infrastructure.Dto.RobotOutputStatusDto

class FactoryCleaningService(
    private val applicationInputDto: ApplicationInputDto,
) {

    private var factory: Factory = Factory(applicationInputDto.factorySizeDto.rows, applicationInputDto.factorySizeDto.columns)
    fun makeCleanUp(): List<RobotOutputStatusDto> {

        val output = ArrayList<RobotOutputStatusDto>();
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
            output.add(RobotOutputStatusDto(robot.status.toString()))
        }

        return output;
    }
}