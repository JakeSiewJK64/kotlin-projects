package au.swin.extensions

import au.swin.mDataClass.AquariumPlant
import au.swin.mDataClass.GreenLeafyPlant

class Extensions {
    companion object {
        fun String.hasSpaces(): Boolean {
            val found = this.find { it == ' ' }
            return found != null
        }

        fun AquariumPlant.print() = println("Aquarium Palnt")
        fun GreenLeafyPlant.print() = println("Green leafy plant")
        fun AquariumPlant?.pull() {
            println("removing $this")
        }
        val AquariumPlant.isGreen: Boolean
            get() = color == "green"
    }
}