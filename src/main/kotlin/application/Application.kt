package application

import infrastructure.ApplicationInputParser

class Application {
    fun executeCleanUp(input: String): List<String> {

        val inputParser = ApplicationInputParser();
        val applicationInputDto = inputParser.parse(input);

        val cleaningService = FactoryCleaningService(applicationInputDto);
        val result = cleaningService.makeCleanUp()

        return result;
    }

}