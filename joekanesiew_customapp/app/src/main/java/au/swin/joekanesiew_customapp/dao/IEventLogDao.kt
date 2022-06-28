package au.swin.joekanesiew_customapp.dao

import au.swin.joekanesiew_customapp.EventLogEnum
import au.swin.joekanesiew_customapp.adapters.EventLogAdapter
import au.swin.joekanesiew_customapp.models.EventLog
import au.swin.joekanesiew_customapp.models.Recipe
import java.util.ArrayList

interface IEventLogDao {
    fun eventLogChangeEventListener(
        eventLogList: ArrayList<EventLog>,
        eventLogAdapter: EventLogAdapter
    )
    fun insertEventLog(
        recipe: Recipe,
        eventDescription: Int,
        eventType: EventLogEnum
    )
}