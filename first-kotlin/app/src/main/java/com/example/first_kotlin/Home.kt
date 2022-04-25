package com.example.first_kotlin

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_home.view.*

class Home : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.mSnackbarButton.setOnClickListener {
            Snackbar.make(view, "This is a snackbar", Snackbar.LENGTH_SHORT)
                .setAction("OK") {
                    println("OK Clicked!")
                }.show()
        }
    }
}