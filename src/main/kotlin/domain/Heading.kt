package domain

enum class Heading(val char: Char) {
    NORTH('N'), EAST('E'), SOUTH('S'), WEST('W');

    companion object {
        fun fromChar(char: Char): Heading {
            return values().find { it.char == char }
                ?: throw IllegalArgumentException("Invalid Heading character: $char")
        }
    }
}