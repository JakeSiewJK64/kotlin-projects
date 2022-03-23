package com.example.first_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        increment_button.setOnClickListener { x -> run{
            counter_text.text = counter++.toString()
        } }
        counter_text.text = counter.toString()
        decrement_button.setOnClickListener { x -> counter_text.text = counter--.toString() }
    }
}