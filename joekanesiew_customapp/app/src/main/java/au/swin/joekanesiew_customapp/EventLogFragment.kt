package au.swin.joekanesiew_customapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        db.collection("joekanesiew-eventlog").addSnapshotListener { value, err ->
            if(err != null) {
                Log.e("ERROR", err.message.toString())
            }
            for(dc: DocumentChange in value?.documentChanges!!) {
                if(dc.type == DocumentChange.Type.ADDED) {
                    eventLogList.add(dc.document.toObject(EventLog::class.java))
                }
            }
            eventLogList.sortByDescending { it.EventDate }
            eventLogAdapter.notifyDataSetChanged()
        }

        eventLogRecyclerView = view.findViewById(R.id.eventLogRecyclerView)
        eventLogAdapter = EventLogAdapter(eventLogList)
        eventLogRecyclerView.adapter = eventLogAdapter
        eventLogRecyclerView.layoutManager = LinearLayoutManager(view.context)
    }
}