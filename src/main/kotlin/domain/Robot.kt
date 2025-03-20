package domain

data class Robot(val status: Status, var factory: Factory) {
    init {
        factory.place(this)
    }
}