package com.example.first_kotlin

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var switchToRegisterTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        switchToRegisterTextView = requireView().findViewById(R.id.registerText)
        super.onViewCreated(view, savedInstanceState)
        switchToRegisterTextView.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.authentication_fragment, RegisterFragment())
                commit()
            }
        }
    }
}