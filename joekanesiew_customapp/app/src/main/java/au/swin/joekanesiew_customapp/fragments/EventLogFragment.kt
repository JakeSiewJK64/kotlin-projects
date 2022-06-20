package au.swin.joekanesiew_customapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.swin.joekanesiew_customapp.GlobalConstants
import au.swin.joekanesiew_customapp.R
import au.swin.joekanesiew_customapp.adapters.EventLogAdapter
import au.swin.joekanesiew_customapp.models.EventLog
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class EventLogFragment : Fragment(R.layout.fragment_event_log) {

    private lateinit var eventLogRecyclerView: RecyclerView
    private lateinit var db: FirebaseFirestore
    private lateinit var eventLogAdapter: EventLogAdapter
    private lateinit var eventLogList: ArrayList<EventLog>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eventLogList = ArrayList()
        db = FirebaseFirestore.getInstance()
        db.collection(GlobalConstants.EVENTLOG_COLLECTION_PATH).addSnapshotListener { value, err ->
            if (err != null) {
                Log.e("ERROR", err.message.toString())
            }
            for (dc: DocumentChange in value?.documentChanges!!) {
                if (dc.type == DocumentChange.Type.ADDED) {
                    eventLogList.add(dc.document.toObject(EventLog::class.java))
                }
            }
            eventLogList.sortByDescending { it.EventDate }
            eventLogAdapter.notifyDataSetChanged()
        }

        eventLogRecyclerView = view.findViewById(R.id.eventLogRecyclerView)
        eventLogAdapter = EventLogAdapter(eventLogList)
        eventLogRecyclerView.apply {
            adapter = eventLogAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }
}