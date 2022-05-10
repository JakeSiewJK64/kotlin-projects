package au.swin.jakesiewjk64_week7_intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var mButton: Button
    private lateinit var mEmailText: TextView
    private lateinit var mPassword: TextView
    private lateinit var rememberCheckbox: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var isChecked = false

        mButton = findViewById(R.id.firstSubmitButton)
        mEmailText = findViewById(R.id.email)
        mPassword = findViewById(R.id.password)
        rememberCheckbox = findViewById(R.id.mCheckBox)

        rememberCheckbox.setOnCheckedChangeListener { _, _ ->
            isChecked = rememberCheckbox.isChecked
        }

        mButton.setOnClickListener {
            val user = User(mPassword.text.toString(), mEmailText.text.toString(), isChecked)
            val i = Intent(this, SecondScreen::class.java).apply {
                putExtra("mUser", user)
            }
            startActivity(i)
        }
    }
}