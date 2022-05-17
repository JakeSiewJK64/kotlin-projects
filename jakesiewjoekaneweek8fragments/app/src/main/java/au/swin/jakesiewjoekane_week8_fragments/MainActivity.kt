package au.swin.jakesiewjoekane_week8_fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnHome = findViewById<Button>(R.id.button1)
        val btnStart = findViewById<Button>(R.id.button2)

        btnStart.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentById(R.id.mFragmentContainer)
            if(fragment != null) {
                supportFragmentManager.beginTransaction().apply {
                    remove(fragment)
                    commit()
                }
            } else {
                super.onBackPressed()
            }
        }

        btnHome.setOnClickListener {
            val fragment1 = Fragment1()
            supportFragmentManager.beginTransaction().apply {
                add(R.id.mFragmentContainer, fragment1)
                commit()
            }
        }
    }
}