import domain.Factory
import domain.Position
import domain.exceptions.OutsideOfTheFactoryBoundariesException
import kotlin.test.Test
import kotlin.test.assertFailsWith

class FactoryShould {

    @Test
    fun raiseAnExceptionIfAskingOutsideTheGrid(){
         val factory = Factory(3,3);
         assertFailsWith<OutsideOfTheFactoryBoundariesException> { factory.whatsIn(Position(3, 4)) }

    }
}
