package au.swin.joekanesiew_core3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Medalist(
    val medalistName: String,
    val goldNum: Int,
    val silverNum: Int,
    val bronzeNum: Int,
    val IOC: String
): Parcelable {
    val totalMedals: Int
        get() = this.goldNum + this.silverNum + this.bronzeNum
    var isTop10 = false
}