package domain

enum class Heading(val char: Char) {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    companion object {
        fun fromChar(c: Char): Heading = values().find { it.char == c } ?: throw IllegalArgumentException("Invalid heading: $c")
    }

    fun rotateRight(): Heading = when (this) {
        NORTH -> EAST
        EAST -> SOUTH
        SOUTH -> WEST
        WEST -> NORTH
    }

    fun rotateLeft(): Heading = when (this) {
        NORTH -> WEST
        WEST -> SOUTH
        SOUTH -> EAST
        EAST -> NORTH
    }
}