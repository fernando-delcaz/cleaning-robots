package application

import infrastructure.ApplicationInputParser
import infrastructure.Dto.RobotOutputStatusDto

class Application {
    fun executeCleanUp(input: String): List<RobotOutputStatusDto> {

        val inputParser = ApplicationInputParser();
        val applicationInputDto = inputParser.parse(input);

        val cleaningService = FactoryCleaningService(applicationInputDto);
        val result = cleaningService.makeCleanUp()

        return result;
    }

}