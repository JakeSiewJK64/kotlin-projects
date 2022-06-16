package au.swin.joekanesiew_core3

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class MedalistAdapter(
    private val medalList: ArrayList<Medalist>, private val supportFragmentManager: FragmentManager
) : RecyclerView.Adapter<MedalistAdapter.MedalistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedalistViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.medalistviewmodel, parent, false) as View
        return MedalistViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedalistViewHolder, position: Int) {
        holder.bind(medalList[position], supportFragmentManager)
    }

    override fun getItemCount(): Int {
        return medalList.size
    }

    inner class MedalistViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val medalIcon: _root_ide_package_.android.widget.ImageView = view.findViewById(R.id.medalIcon)
        private val medalName: TextView = view.findViewById(R.id.medalistNameText)
        private val medalNum: TextView = view.findViewById(R.id.medalNumberText)
        private val sharedPref = view.context.getSharedPreferences("LastClickedMedalist", MODE_PRIVATE)
        private val editSharedPref = sharedPref.edit()

        fun bind(medalist: Medalist, supportFragmentManager: FragmentManager) {
            medalName.text = medalist.medalistName
            medalNum.text = medalist.totalMedals.toString()

            // todo: check if medalist is top 10. if true, display star icon
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
}