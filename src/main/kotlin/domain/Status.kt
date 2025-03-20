package domain

data class Status (val position: Position, val heading: Heading){

    override fun toString(): String {
        return "${position.x} ${position.y} ${heading.char}"
    }
}