package integration

import application.FactoryCleaningService
import domain.*
import infrastructure.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationShould {

    @Test
    fun handleOneRobotToCleanOneFactory() {

        val factorySizeDto = FactorySizeDto(5, 5);
        val robotInitialStatus = "1 2 N";

        val robotInitialStatusParser = RobotStatusParser();
        val robotInstructionParser = RobotInstructionParser();

        val applicationInputDto = ApplicationInputDto(factorySizeDto, listOf(Pair(robotInitialStatusParser.parse(robotInitialStatus), listOf())));
        var factoryCleaningService = FactoryCleaningService(applicationInputDto);

        val robotInstructions = "LMLMLMLMM";
        val robotInstructionDto = RobotMovementDto(robotInitialStatus, robotInstructions);
        val robotInstructionsMovementDtos = listOf(robotInstructionDto);

        val expectedRobotsFinalStatus = "1 3 N";
        val robotsFinalStatus = factoryCleaningService.makeCleanUp(robotInstructionsMovementDtos);

        assertEquals(expectedRobotsFinalStatus, robotsFinalStatus.get(0));
    }
}
