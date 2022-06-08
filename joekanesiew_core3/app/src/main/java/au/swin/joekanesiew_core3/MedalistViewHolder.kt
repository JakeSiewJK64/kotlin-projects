package au.swin.joekanesiew_core3

import android.content.Context.MODE_PRIVATE
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class MedalistViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val medalName: TextView = view.findViewById(R.id.medalistNameText)
    private val medalNum: TextView = view.findViewById(R.id.medalNumberText)
    private val sharedPref = view.context.getSharedPreferences("LastClickedMedalist", MODE_PRIVATE)
    private val editSharedPref = sharedPref.edit()

    fun bind(medalist: Medalist) {
        medalName.text = medalist.medalistName
        medalNum.text = medalist.medalistNum.toString()
        view.setOnClickListener {
            Snackbar.make(
                view,
                "${medalist.medalistName} has score of ${medalist.medalistNum}!",
                Snackbar.LENGTH_LONG
            )
                .setAction("OK") {}.show()
            editSharedPref.putString("LAST_CLICKED", medalist.medalistName)
            editSharedPref.apply()
        }
    }
}