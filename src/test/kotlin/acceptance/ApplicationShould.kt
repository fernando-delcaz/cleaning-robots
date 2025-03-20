package acceptance

import application.FactoryCleaningService
import domain.*
import infrastructure.FactorySizeDto
import infrastructure.RobotMovementDto
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ApplicationShould {

    @Test
    fun handleOneRobotToCleanOneFactory() {

        val factorySizeDto = FactorySizeDto(5, 5);
        val robotInitialStatus = "1 2 N";

        val robotInitialStatusParser: (String) -> Status = { _ -> Status(Position(1, 2), Heading.NORTH)}
        val robotInstructionParserStub: (String) -> List<Instruction> = {
            listOf(
                Rotation(Direction.LEFT),
                ForwardMovement(),
                Rotation(Direction.RIGHT),
                ForwardMovement(),
                Rotation(Direction.LEFT),
                ForwardMovement(),
                Rotation(Direction.RIGHT),
                ForwardMovement(),
                Rotation(Direction.LEFT),
                ForwardMovement(),
                Rotation(Direction.RIGHT),
                ForwardMovement()
            )
        }

        var factoryCleaningService = FactoryCleaningService(factorySizeDto, robotInitialStatusParser, robotInstructionParserStub);

        val robotInstructions = "LMLMLMLMM";
        val robotInstructionDto = RobotMovementDto(robotInitialStatus, robotInstructions);
        val robotInstructionsMovementDtos = listOf(robotInstructionDto);

        val expectedRobotsFinalStatus = "1 3 N";
        val robotsFinalStatus = factoryCleaningService.makeCleanUp(robotInstructionsMovementDtos);
        assertEquals(expectedRobotsFinalStatus, robotsFinalStatus.get(0));
    }
}

