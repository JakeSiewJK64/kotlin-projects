package au.swin.jakesiewjoekane_week8_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class Fragment2 : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_2, container, false)
        val args = this.arguments
        val textResult = view.findViewById<TextView>(R.id.f2Text)
        val inputData = args?.getString("NAME")
        textResult.text = "Welcome ${inputData.toString()} to fragment 2"
        return view
    }
}