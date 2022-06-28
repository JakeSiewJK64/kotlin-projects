package au.swin.joekanesiew_customapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.swin.joekanesiew_customapp.R
import au.swin.joekanesiew_customapp.adapters.EventLogAdapter
import au.swin.joekanesiew_customapp.dao.EventLogDao
import au.swin.joekanesiew_customapp.models.EventLog
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
        val eventLogDao = EventLogDao(db, view)

        eventLogRecyclerView = view.findViewById(R.id.eventLogRecyclerView)
        eventLogAdapter = EventLogAdapter(eventLogList)

        eventLogDao.eventLogChangeEventListener(eventLogList, eventLogAdapter)

        eventLogRecyclerView.apply {
            adapter = eventLogAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
    }
}