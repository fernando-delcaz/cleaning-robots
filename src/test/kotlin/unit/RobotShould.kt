package unit

import domain.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RobotShould {

     @Test
     fun beAssignedToOneFactory(){
         val factory = Factory(5, 5);
         val robot = Robot(Status(Position(0,0), Heading.NORTH), factory);

         assertEquals(robot, factory.whatsIn(robot.status.position));
         assertEquals(robot.factory, factory);
     }

    @Test
    fun rotateRight(){
        val factory = Factory(5, 5);
        val robot = Robot(Status(Position(0,0), Heading.NORTH), factory);
        val expectedStatus = Status(Position(0,0), Heading.EAST);

        val instruction = Rotation(Direction.RIGHT);
        robot.updateStatus(instruction)

        assertEquals(robot.status, expectedStatus);
    }
}


