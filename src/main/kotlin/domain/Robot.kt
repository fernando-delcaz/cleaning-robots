package domain

class Robot(private var heading: Heading) {

    fun executeRotation(instruction: Char) {
        //instructions.forEach { command ->
            when (instruction) {
                'L' -> heading = heading.rotateLeft()
                'R' -> heading = heading.rotateRight()
                else -> throw IllegalArgumentException("Robot can only turn left or right and move forward")
            }
        //}
    }

    fun whereAreYouPointingTo(): Heading {
        return heading;
    }
}