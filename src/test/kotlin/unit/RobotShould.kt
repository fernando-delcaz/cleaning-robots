package unit

import domain.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class RobotShould {

     @Test
     fun beAbleToBeAssignedToOneFactory(){
         val robot = Robot(Status(Position(0,0), Heading.NORTH));
         val factory = Factory(5, 5);

         robot.assignTo(factory);
         assertEquals(robot, factory.whatsIn(robot.status.position));
         assertEquals(robot.factory, factory);
     }
}