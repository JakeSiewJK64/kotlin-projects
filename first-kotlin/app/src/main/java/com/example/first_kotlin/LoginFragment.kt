package com.example.first_kotlin

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import models.UserModel

class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var switchToRegisterTextView: TextView

    private lateinit var loginEmail: EditText
    private lateinit var loginPassword: EditText
    private lateinit var loginButton: Button
    private lateinit var db: FirebaseFirestore

    private fun verifyUser(username: String, userPassword: String) {
        db = FirebaseFirestore.getInstance()

        db.collection("mUsers").addSnapshotListener { value, error ->
            if (error != null) {
                Log.e("ERORR", error.message.toString())
            }

            for (dc: DocumentChange in value?.documentChanges!!) {
                var myData = dc.document.toObject(UserModel::class.java)
                if (myData.muserName == username && myData.muserPassword == userPassword) {
                    Snackbar.make(loginButton, "Login Successful", Snackbar.LENGTH_LONG).show()
                    return@addSnapshotListener
                }
            }
            Snackbar.make(loginButton, "Login Failed", Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switchToRegisterTextView = requireView().findViewById(R.id.registerText)
        loginEmail = requireView().findViewById(R.id.loginEmailEditText)
        loginPassword = requireView().findViewById(R.id.loginPasswordEditText)
        loginButton = requireView().findViewById(R.id.loginButton)

        loginButton.setOnClickListener {
            verifyUser(loginEmail.text.toString(), loginPassword.text.toString())
            Snackbar.make(loginButton, "Logging in...", Snackbar.LENGTH_LONG).show()
        }

        switchToRegisterTextView.setOnClickListener {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.authentication_fragment, RegisterFragment())
                commit()
            }
        }
    }
}