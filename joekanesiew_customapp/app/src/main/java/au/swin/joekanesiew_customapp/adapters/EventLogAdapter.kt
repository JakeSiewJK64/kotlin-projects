package au.swin.joekanesiew_customapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.swin.joekanesiew_customapp.R
import au.swin.joekanesiew_customapp.models.EventLog

class EventLogAdapter(private val eventList: ArrayList<EventLog>) :
    RecyclerView.Adapter<EventLogAdapter.EventLogViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventLogViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.event_log_viewmodel, parent, false) as View
        return EventLogViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventLogViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    inner class EventLogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val eventLogName = view.findViewById<TextView>(R.id.EventDescription)
        private val eventLogDate = view.findViewById<TextView>(R.id.EventDate)
        private val eventLogType = view.findViewById<TextView>(R.id.EventType)

        fun bind(event: EventLog) {
            eventLogName.text = event.EventDescription
            eventLogDate.text = event.EventDate
            eventLogType.text = event.Type.toString()
        }
    }
}