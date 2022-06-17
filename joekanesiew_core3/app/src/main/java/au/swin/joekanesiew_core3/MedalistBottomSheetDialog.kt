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
        return inflater.inflate(R.layout.medalist_fragment_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val medalist: MedalistModel? = arguments?.getParcelable("[DATA]:MEDALIST")
        Log.i("DATA", medalist.toString())
        medalist?.apply {
            view.apply {
                findViewById<TextView>(R.id.modalCountryName).text = name
                findViewById<TextView>(R.id.modalCountryGold).text =
                    String.format(resources.getString(R.string.gold_medals), medalist.gold)
                findViewById<TextView>(R.id.modalCountrySilver).text =
                    String.format(resources.getString(R.string.silver_medals), medalist.silver)
                findViewById<TextView>(R.id.modalCountryBronze).text =
                    String.format(resources.getString(R.string.bronze_medals), medalist.bronze)
                findViewById<TextView>(R.id.modalIOC).text = IOC
            }
        }
    }
}