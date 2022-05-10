package au.swin.mDataClass

class PrintingFishAction(val food: String) : FishAction {
    override fun eat() {
        println("eats ${food}")
    }
}