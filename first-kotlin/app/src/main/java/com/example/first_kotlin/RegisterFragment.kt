package com.example.first_kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class RegisterFragment : Fragment(R.layout.fragment_register) {
    private lateinit var switchToLoginTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        switchToLoginTextView = requireView().findViewById(R.id.loginText)
        switchToLoginTextView.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.authentication_fragment, LoginFragment())
                commit()
            }
        }
    }
}