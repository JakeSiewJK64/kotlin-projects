package au.swin.fish

import au.swin.mDataClass.FishAction
import au.swin.mDataClass.FishColor

class Shark(override val color: String = "gray") : FishColor, FishAction {
    override fun eat() {
        println("hunt and eat fish")
    }
}