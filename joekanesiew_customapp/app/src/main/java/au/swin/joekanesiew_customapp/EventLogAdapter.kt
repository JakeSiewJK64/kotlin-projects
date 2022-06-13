package au.swin.joekanesiew_customapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EventLogAdapter(private val eventList: ArrayList<EventLog>) : RecyclerView.Adapter<EventLogViewHolder>(){
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
}