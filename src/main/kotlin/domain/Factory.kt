package domain

private const val MIN_COLUMNS = 1
private const val MIN_ROWS = 1

class Factory(private val rows: Int, private val columns : Int) {

    init {
        if (rows < MIN_ROWS || columns < MIN_COLUMNS) {
            throw IllegalArgumentException("Grid size must be greater than zero.")
        }
    }

    private val factoryFloor: Array<Array<Robot?>> = Array(columns) { Array(rows) { null } }

    fun place(robot: Robot, positionX: Int, positionY : Int) {
        factoryFloor[positionY - 1][positionX - 1] = robot;
    }

    fun whatsIn(positionX: Int, positionY: Int): Robot? {
        if (positionX in 1..rows && positionY in 1..columns) {
            factoryFloor[positionY - 1][positionX - 1]
        }
        return null;
    }
}