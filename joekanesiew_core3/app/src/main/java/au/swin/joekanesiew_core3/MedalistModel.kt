package au.swin.joekanesiew_core3

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedalistModel(
    val name: String,
    val IOC: String,
    val gold: Int,
    val silver: Int,
    val bronze: Int
): Parcelable {
    val totalMedals: Int
        get() = this.gold + this.silver + this.bronze
    var top10 = false
}