package com.example.first_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_counter.*

class CounterActivity : AppCompatActivity() {
    var counter = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_counter)

        increment_button.setOnClickListener { _ -> run{
            counter_text.text = counter++.toString()
            println("increment clicked!")
        } }
        counter_text.text = counter.toString()
        decrement_button.setOnClickListener { _ -> counter_text.text = counter--.toString() }
    }
}