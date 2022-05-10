package au.swin.mDataClass

open class Aquarium(
    open var length: Int = 100,
    open var width: Int = 20,
    open var height: Int = 40
) {

    open var volume: Int
        get() = width * length * height / 1000
        set(value) {
            height = (value * 1000) / (width * length)
        }

    open var shape = "rectangle"
    open var water: Double
        get() = .9 * volume
        set(value) {}

    init {
        println("aquarium initializing")
    }

    constructor(numberOfFish: Int) : this() {
        val tank = numberOfFish * 2000 * 1.1
        height = (tank / (length * width)).toInt()
    }

    fun printSize() {
        println(shape)
        println(
            "Width: $width cm " +
                    "Length: $length cm " +
                    "Height: $height cm "
        )
        // 1 l = 1000 cm^3
        println("Volume: $volume l Water: $water l (${water / volume * 100.0}% full)")
    }
}