package domain

data class Robot(val status: Status) {
    lateinit var factory: Factory

    fun assignTo(factory: Factory) {
        this.factory = factory;
        factory.place(this);
    }

}