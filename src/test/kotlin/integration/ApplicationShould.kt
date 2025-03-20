package integration

import application.FactoryCleaningService
import domain.*
import infrastructure.FactorySizeDto
import infrastructure.RobotMovementDto
import infrastructure.RobotStatusParser
import org.junit.jupiter.api.Test
import unit.RobotInstructionParser
import kotlin.test.assertEquals

class ApplicationShould {

    @Test
    fun handleOneRobotToCleanOneFactory() {

        val factorySizeDto = FactorySizeDto(5, 5);
        val robotInitialStatus = "1 2 N";

        val robotInitialStatusParser = RobotStatusParser();
        val robotInstructionParser = RobotInstructionParser();

        var factoryCleaningService = FactoryCleaningService(factorySizeDto, robotInitialStatusParser::parse, robotInstructionParser::parse);

        val robotInstructions = "LMLMLMLMM";
        val robotInstructionDto = RobotMovementDto(robotInitialStatus, robotInstructions);
        val robotInstructionsMovementDtos = listOf(robotInstructionDto);

        val expectedRobotsFinalStatus = "1 3 N";
        val robotsFinalStatus = factoryCleaningService.makeCleanUp(robotInstructionsMovementDtos);
        assertEquals(expectedRobotsFinalStatus, robotsFinalStatus.get(0));
    }
}
