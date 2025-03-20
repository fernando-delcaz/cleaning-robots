package infrastructure

import domain.Instruction
import domain.Status

class ApplicationInputParser {

    val factoryInputParser = FactoryInputParser();
    val robotStatusParser = RobotStatusParser();
    val robotInstructionParser = RobotInstructionParser();

    fun parse(input: String): ApplicationInputDto {
        val inputLines = input.trim().lines();
        val factorySizeDto = factoryInputParser.parse(inputLines[0]);

        val robots = mutableListOf<Pair<StatusDto, List<InstructionDto>>>()
        for (i in 1 until inputLines.size step 2) {
            val robotStatusDto = robotStatusParser.parse(inputLines[i].trim())
            val robotInstructionsDto = robotInstructionParser.parse(inputLines[i + 1].trim())
            robots.add(Pair(robotStatusDto, robotInstructionsDto))
        }

        return ApplicationInputDto(factorySizeDto, robots)
    }
}