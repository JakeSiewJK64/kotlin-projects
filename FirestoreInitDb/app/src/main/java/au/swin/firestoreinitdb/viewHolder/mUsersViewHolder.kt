package au.swin.firestoreinitdb.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import au.swin.firestoreinitdb.R

class mUsersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.mUser_ViewHolder)
    fun bind(name: String) {
        textView.text = name
    }
}