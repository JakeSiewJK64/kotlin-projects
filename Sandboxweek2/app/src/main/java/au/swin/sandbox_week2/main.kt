package au.swin.sandbox_week2

import au.swin.decor.Decoration
import au.swin.enums.Direction
import au.swin.extensions.Extensions.Companion.pull
import au.swin.fish.Plecostomus
import au.swin.fish.Shark
import au.swin.mDataClass.Aquarium
import au.swin.mDataClass.AquariumPlant
import au.swin.mDataClass.TowerTank
import au.swin.sealed.SeaLion
import au.swin.sealed.Seal
import au.swin.sealed.Walrus

var sum2: (Int, Int) -> Int = { a, b -> a + b }
var sum3 = { a: Int, b: Int -> a + b }
fun main() {
    val myList: MutableList<Int> = mutableListOf(1,2,3)
    myList.also{

    }
}

fun pointDirection() {
    println(Direction.EAST.name)
    println(Direction.EAST.ordinal)
    println(Direction.EAST.degrees)
}

fun matchSeal(seal: Seal): String {
    return when (seal) {
        is Walrus -> "walrus"
        is SeaLion -> "sea lion"
    }
}

fun makeDecorations() {
    val d5 = Decoration("crystals", "wood", "diver")
    println(d5)
    val (rocks, wood, diver) = d5
    println(rocks)
    println(wood)
    println(diver)
}

fun makeFish() {
    val mShark = Shark()
    val mPlecostomus = Plecostomus()
    mShark.eat()
    mPlecostomus.eat()
}

fun buildAquarium() {
    val aquarium6 = Aquarium(length = 25, width = 25, height = 40)
    aquarium6.printSize()
    val myTower = TowerTank(diameter = 25, height = 40)
    myTower.printSize()
}