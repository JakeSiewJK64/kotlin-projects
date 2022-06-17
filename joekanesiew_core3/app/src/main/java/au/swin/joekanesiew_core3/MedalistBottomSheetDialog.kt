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

        val goldTV: TextView = view.findViewById(R.id.bottomSheetGold)
        val silverTV: TextView = view.findViewById(R.id.bottomSheetSilver)
        val bronzeTV: TextView = view.findViewById(R.id.bottomSheetBronze)
        val cName: TextView = view.findViewById(R.id.bottomSheetCountryName)
        val cIOC: TextView = view.findViewById(R.id.bottomSheetIOC)

        Log.i("DATA", medalist.toString())
        medalist?.apply {
            view.apply {
                cName.text = name
                goldTV.text =
                    String.format(resources.getString(R.string.gold_medals), medalist.gold)
                silverTV.text =
                    String.format(resources.getString(R.string.silver_medals), medalist.silver)
                bronzeTV.text =
                    String.format(resources.getString(R.string.bronze_medals), medalist.bronze)
                cIOC.text = IOC
            }
        }
    }
}