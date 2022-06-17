package au.swin.joekanesiew_core3

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CustomBottomSheet : BottomSheetDialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.medalistbottomsheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val medalist: Medalist? = arguments?.getParcelable("[DATA]:MEDALIST")
        Log.i("DATA", medalist.toString())
        medalist?.let {
            view.findViewById<TextView>(R.id.modalCountryName).text = it.medalistName
            view.findViewById<TextView>(R.id.modalCountryGold).text =
                String.format(resources.getString(R.string.gold_medals), medalist.goldNum)
            view.findViewById<TextView>(R.id.modalCountrySilver).text =
                String.format(resources.getString(R.string.silver_medals), medalist.silverNum)
            view.findViewById<TextView>(R.id.modalCountryBronze).text =
                String.format(resources.getString(R.string.bronze_medals), medalist.bronzeNum)
            view.findViewById<TextView>(R.id.modalIOC).text = it.IOC
        }
    }
}