package au.swin.joekanesiew_customapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventLogViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val eventLogName = view.findViewById<TextView>(R.id.EventDescription)
    private val eventLogDate = view.findViewById<TextView>(R.id.EventDate)
    private val eventLogType = view.findViewById<TextView>(R.id.EventType)

    fun bind(event: EventLog) {
        eventLogName.text = event.EventDescription
        eventLogDate.text = event.EventDate
        eventLogType.text = event.Type.toString()
    }
}