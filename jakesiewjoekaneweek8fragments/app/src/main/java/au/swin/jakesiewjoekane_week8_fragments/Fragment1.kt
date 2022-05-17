package au.swin.jakesiewjoekane_week8_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class Fragment1 : Fragment(R.layout.fragment_1) {
    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)
        val btnText = view?.findViewById<EditText>(R.id.f1TextArea)
        val btnFragmentSubmit = view?.findViewById<Button>(R.id.f1SubmitButton)
        btnFragmentSubmit?.setOnClickListener {
            val input = btnText?.text.toString()
            val bundle = Bundle()
            val fragment2 = Fragment2()
            bundle.putString("NAME", input)
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mFragmentContainer, fragment2)
                commit()
            }
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}