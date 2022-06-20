package au.swin.joekanesiew_customapp.models

import android.os.Parcelable
import au.swin.joekanesiew_customapp.EventLogEnum
import kotlinx.android.parcel.Parcelize

@Parcelize
data class EventLog(
    var ObjectId: String? = "",
    var EventDescription: String? = "",
    var EventDate: String? = "",
    var Type: EventLogEnum? = EventLogEnum.CREATE
) : Parcelable
