package au.swin.joekanesiew_core3

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.ImageView
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class MedalistAdapter(
    private val adapterSupportFragmentManager: FragmentManager,
    private val medalList: ArrayList<MedalistModel>
) : RecyclerView.Adapter<MedalistAdapter.MedalistViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedalistViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.medalist_view_model, parent, false) as View
        return MedalistViewHolder(view)
    }

    override fun onBindViewHolder(holder: MedalistViewHolder, position: Int) {
        holder.bind(medalList[position])
    }

    override fun getItemCount(): Int {
        return medalList.size
    }

    // inner class to contain view holder
    inner class MedalistViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        private val medalistSharePrefs =
            view.context.getSharedPreferences(MedalistContants.MEDALLISTS_SHARE_PREFS, MODE_PRIVATE)
        private val editSharedPrefs = medalistSharePrefs.edit()
        private val medalNum: TextView = view.findViewById(R.id.medalistViewModelNumber)
        private val medalName: TextView = view.findViewById(R.id.medalistViewModelName)
        private val medalIcon: ImageView = view.findViewById(R.id.medalistViewModelIcon)
        private val medalLayout: LinearLayout = view.findViewById(R.id.medalistViewModelLayout)

        // bind function to bind data to single view model
        fun bind(medalist: MedalistModel) {
            medalNum.text = medalist.totalMedals.toString()
            medalName.text = medalist.name

            /**
             * if medallist attribute isTop10 is true, set ImageView to star icon
             * else set image resource to 0
             **/
            if (medalist.top10) {
                medalIcon.setImageResource(R.drawable.crown)
                medalLayout.setBackgroundColor(
                    view.context.resources.getColor(
                        R.color.yellow,
                        null
                    )
                )
            } else {
                medalIcon.setImageResource(0)
                medalLayout.setBackgroundColor(
                    view.context.resources.getColor(
                        R.color.white,
                        null
                    )
                )
            }

            view.setOnClickListener {
                val b = CustomBottomSheet()
                val bundle = Bundle()
                bundle.putParcelable("[DATA]:MEDALIST", medalist)
                b.arguments = bundle
                b.show(adapterSupportFragmentManager, "[TAG]:MEDALIST")

                /**
                 * using shared preferences to store
                 * 1. last clicked country
                 * 2. last clicked country IOC
                 **/
                editSharedPrefs.apply {
                    putString("[DATA]:PREVIOUS_MEDALIST_NAME", medalist.name)
                    putString("[DATA]:PREVIOUS_MEDALIST_IOC", medalist.IOC)
                    apply()
                }
            }
        }
    }
}