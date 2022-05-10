package com.example.first_kotlin

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.view.*
import models.UserModel

class Home : Fragment(R.layout.fragment_home) {

    private lateinit var db: FirebaseFirestore
    private lateinit var mNameEditText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = FirebaseFirestore.getInstance()
        mNameEditText = requireView().findViewById(R.id.nameEditText)

        view.mSnackbarButton.setOnClickListener {
            Snackbar.make(view, "This is a snackbar", Snackbar.LENGTH_SHORT)
                .setAction("OK") {
                    println("OK Clicked!")
                }.show()
        }

        view.nameSubmitButton.setOnClickListener {
            postUser(mNameEditText.text.toString(), view)
        }
    }

    private fun postUser(name: String, view: View) {
        val newUser = UserModel(muserName = name, "welcome123")
        val docId = db.collection("mUsers").document().id
        db.collection("mUsers").document(docId).set(newUser).addOnSuccessListener {
            Snackbar.make(view, "Saved User", Snackbar.LENGTH_SHORT)
                .setAction("OK") {
                    println("OK Clicked!")
                }.show()
        }
    }
}