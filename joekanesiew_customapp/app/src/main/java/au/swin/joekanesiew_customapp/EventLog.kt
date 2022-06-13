package au.swin.joekanesiew_customapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventLog(
    var ObjectId: String? = "",
    var EventDescription: String? = "",
    var EventDate: String? = "",
    var Type: EventLogEnum? = EventLogEnum.CREATE
) : Parcelable
