package au.swin.joekanesiew_week7_core2

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val locationName: String,
    val locationRating: Int,
    val locationDate: String,
    val locationType: String,
    val locationImage: Int
): Parcelable
