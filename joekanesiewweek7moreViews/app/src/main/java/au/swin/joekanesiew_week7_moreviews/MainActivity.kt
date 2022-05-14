package au.swin.joekanesiew_week7_moreviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private lateinit var usernameLayout: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        usernameLayout = findViewById(R.id.myUsernameLayout)

        usernameLayout.setOnFocusChangeListener { _, focus ->
            if(focus) {
                validateName()
            }
        }
    }

    private fun validateAge(): String? {
        return null
    }

    private fun validateName(): String?{
        return null
    }
}