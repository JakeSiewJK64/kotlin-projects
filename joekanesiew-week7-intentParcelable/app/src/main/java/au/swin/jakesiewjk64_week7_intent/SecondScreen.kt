package au.swin.jakesiewjk64_week7_intent

import android.os.Bundle
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SecondScreen : AppCompatActivity() {

    private lateinit var mEmailText: TextView
    private lateinit var secondCheckBox: CheckBox
    private lateinit var secondEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_screen)

        mEmailText = findViewById(R.id.secondTextView)
        secondCheckBox = findViewById(R.id.secondCheckbox)
        secondEditText = findViewById(R.id.secondEditText)

        intent.getParcelableExtra<User>("mUser")?.let {
            mEmailText.text = it.userEmail
            secondEditText.setText(it.userPassword)
            secondCheckBox.isChecked = it.rememberUser.toString().toBoolean()
        }
    }
}