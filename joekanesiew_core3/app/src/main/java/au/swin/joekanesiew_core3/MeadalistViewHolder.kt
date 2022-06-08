package au.swin.joekanesiew_core3

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MeadalistViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

    private val medalName: TextView = view.findViewById(R.id.medalistNameText)
    private val medalNum: TextView = view.findViewById(R.id.medalNumberText)

    fun bind(medalist: Medalist) {
        medalName.text = medalist.medalistName
        medalNum.text = medalist.medalistNum.toString()
        view.setOnClickListener {
            Snackbar.make(view, "${medalist.medalistName} clicked!", Snackbar.LENGTH_LONG)
                .setAction("OK") {}.show()
        }
    }
}