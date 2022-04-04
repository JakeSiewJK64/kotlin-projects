package au.swin.fish

import au.swin.mDataClass.FishAction
import au.swin.mDataClass.FishColor
import au.swin.mDataClass.GoldColor
import au.swin.mDataClass.PrintingFishAction

class Plecostomus(fishColor: FishColor = GoldColor) :
    FishAction by PrintingFishAction("algae"),
    FishColor by fishColor {
}