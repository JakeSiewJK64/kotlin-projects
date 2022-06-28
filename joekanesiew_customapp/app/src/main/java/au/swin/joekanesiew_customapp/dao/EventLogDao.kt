package au.swin.joekanesiew_customapp.dao

import android.util.Log
import android.view.View
import au.swin.joekanesiew_customapp.EventLogEnum
import au.swin.joekanesiew_customapp.GlobalConstants
import au.swin.joekanesiew_customapp.adapters.EventLogAdapter
import au.swin.joekanesiew_customapp.models.EventLog
import au.swin.joekanesiew_customapp.models.Recipe
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class EventLogDao(
    private val firestoreInstance: FirebaseFirestore,
    private val view: View,
) : IEventLogDao {

    // method will detect any changes to firestore event log collection
    // if changes were made, update/populate the event log ArrayList
    override fun eventLogChangeEventListener(
        eventLogList: ArrayList<EventLog>,
        eventLogAdapter: EventLogAdapter
    ) {
        val collection = firestoreInstance.collection(GlobalConstants.EVENTLOG_COLLECTION_PATH)
        collection.addSnapshotListener { value, err ->
            if (err != null) {
                Log.e("ERROR", err.message.toString())
            }

            for (dc: DocumentChange in value?.documentChanges!!) {
                if (dc.type == DocumentChange.Type.ADDED) {
                    eventLogList.add(dc.document.toObject(EventLog::class.java))
                }
            }
            eventLogList.sortBy { x -> x.EventDate }
            Log.i("DATA", eventLogList.toString())
            eventLogAdapter.notifyDataSetChanged()
        }
    }

    override fun insertEventLog(
        recipe: Recipe,
        eventDescription: Int,
        eventType: EventLogEnum
    ) {
        val eventLogCollection =
            firestoreInstance.collection(GlobalConstants.EVENTLOG_COLLECTION_PATH)
        eventLogCollection.document().set(
            EventLog(
                null,
                String.format(
                    view.resources.getString(eventDescription),
                    recipe.RecipeName
                ),
                SimpleDateFormat(
                    GlobalConstants.GLOBAL_DATE_FORMAT,
                    Locale.getDefault()
                ).format(Calendar.getInstance().time),
                eventType
            )
        )
    }
}