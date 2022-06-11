package au.swin.joekanesiew_core3

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class MedalistViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val medalIcon: ImageView = view.findViewById(R.id.medalIcon)
    private val medalName: TextView = view.findViewById(R.id.medalistNameText)
    private val medalNum: TextView = view.findViewById(R.id.medalNumberText)
    private val sharedPref = view.context.getSharedPreferences("LastClickedMedalist", MODE_PRIVATE)
    private val editSharedPref = sharedPref.edit()

    fun bind(medalist: Medalist, supportFragmentManager: FragmentManager) {
        medalName.text = medalist.medalistName
        medalNum.text = medalist.totalMedals.toString()

        if(medalist.isTop10) {
            medalIcon.setImageResource(R.drawable.star)
        } else {
            medalIcon.setImageResource(0)
        }

        view.setOnClickListener {

            // todo: get support fragment manager
            val b = MedalistBottomSheetDialog()
            val bundle = Bundle()
            bundle.putParcelable("MEDALIST_DATA", medalist)
            b.arguments = bundle
            b.show(supportFragmentManager, "MEDALIST")

            // todo: stores clicked country name into shared preferences
            editSharedPref.putString("LAST_CLICKED_COUNTRY_NAME", medalist.medalistName)
            editSharedPref.putString("LAST_CLICKED_COUNTRY_IOC", medalist.IOC)
            editSharedPref.apply()
        }
    }
}