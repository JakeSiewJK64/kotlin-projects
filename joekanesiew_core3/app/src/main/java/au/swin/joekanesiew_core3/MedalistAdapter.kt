package au.swin.joekanesiew_core3

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class MedalistAdapter(
    private val supportFragmentManager: FragmentManager,
    private val medalList: ArrayList<Medalist>
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

    // inner class to contain view holder
    inner class MedalistViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val sharedPref =
            view.context.getSharedPreferences("[DATA]:PREVIOUS_MEDALIST", MODE_PRIVATE)
        private val editSharedPref = sharedPref.edit()
        private val medalNum: TextView = view.findViewById(R.id.medalNumberText)
        private val medalName: TextView = view.findViewById(R.id.medalistNameText)
        private val medalIcon: ImageView = view.findViewById(R.id.medalIcon)

        // bind function to bind data to single view model
        fun bind(medalist: Medalist, supportFragmentManager: FragmentManager) {
            medalNum.text = medalist.totalMedals.toString()
            medalName.text = medalist.medalistName

            /**
             * if medallist attribute isTop10 is true, set ImageView to star icon
             * else set image resource to 0
             **/
            if (medalist.isTop10) {
                medalIcon.setImageResource(R.drawable.star)
            } else {
                medalIcon.setImageResource(0)
            }

            view.setOnClickListener {
                val b = CustomBottomSheet()
                val bundle = Bundle()
                bundle.putParcelable("[DATA]:MEDALIST", medalist)
                b.arguments = bundle
                b.show(supportFragmentManager, "MEDALIST")

                /**
                 * using shared preferences to store
                 * 1. last clicked country
                 * 2.last clicked country IOC
                 **/
                editSharedPref.putString("LAST_CLICKED_COUNTRY_IOC", medalist.IOC)
                editSharedPref.putString("LAST_CLICKED_COUNTRY_NAME", medalist.medalistName)
                editSharedPref.apply()
            }
        }
    }
}