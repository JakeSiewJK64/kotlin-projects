package com.example.first_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Authentication : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.authentication_fragment, LoginFragment())
            commit()
        }
    }
}