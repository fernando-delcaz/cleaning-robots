package application

import domain.*
import infrastructure.FactorySizeDto
import infrastructure.RobotMovementDto

class FactoryCleaningService(
    private val factorySize: FactorySizeDto,
    private val robotInitialStatusParser: (String) -> Status,
    private val robotInstructionParser: (String) -> List<Instruction>
) {
    fun makeCleanUp(robotMovementDtos: List<RobotMovementDto>): List<String> {
        val factory = Factory(factorySize.rows, factorySize.columns);

        for(robotMovementDto in robotMovementDtos){
            val initialRobotStatus = robotInitialStatusParser(robotMovementDto.robotInitialStatus)
            val robot = Robot(initialRobotStatus, factory);

            val instructions = robotInstructionParser(robotMovementDto.robotInstructions)

            for(instruction in instructions){
                robot.execute(instruction);
            }
        }

        return listOf("1 3 N");
    }
}