package domain

import domain.exceptions.OutsideOfTheFactoryBoundariesException
import domain.exceptions.TileAlreadyOccupiedException
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class FactoryTest {

    private lateinit var factoryFloor: FactoryFloor
    private lateinit var factory: Factory
    private lateinit var robot: Robot

    @BeforeEach
    fun setUp() {
        factoryFloor = mockk(relaxed = true)
        factory = Factory(5, 5)
        factory.factoryFloor = factoryFloor
        robot = Robot(Status(Position(2, 2), Heading.NORTH), factory)
    }

    @Test
    fun `should move robot to a new position if tile is not occupied`() {
        val newStatus = Status(Position(3, 2), Heading.NORTH)

        every { factoryFloor.isTileOccupied(newStatus.position) } returns false
        every { factoryFloor.clearTile(robot) } just Runs
        every { factoryFloor.updateTileContent(robot) } just Runs

        val result = factory.moveRobot(robot, newStatus)

        assertEquals(newStatus, result)
        verify { factoryFloor.clearTile(robot) }
        verify { factoryFloor.updateTileContent(robot) }
    }

    @Test
    fun `should not move robot if new position is out of bounds`() {
        val newStatus = Status(Position(6, 6), Heading.NORTH)

        every { factoryFloor.clearTile(robot) } just Runs
        every { factoryFloor.updateTileContent(robot) } throws OutsideOfTheFactoryBoundariesException("Out of bounds!")


        assertFailsWith<OutsideOfTheFactoryBoundariesException> { factory.moveRobot(robot, newStatus)}
        verify { factoryFloor.clearTile(robot) }
        verify { factoryFloor.updateTileContent(robot) }
    }

    @Test
    fun `should not move robot if new position is occupied`() {
        val newStatus = Status(Position(3, 2), Heading.NORTH)

        every { factoryFloor.clearTile(robot) } just Runs
        every { factoryFloor.updateTileContent(robot) } throws TileAlreadyOccupiedException("Tile occupied!")

        assertFailsWith<TileAlreadyOccupiedException> { factory.moveRobot(robot, newStatus)}
        verify { factoryFloor.clearTile(robot) }
        verify { factoryFloor.updateTileContent(robot) }
    }
}
