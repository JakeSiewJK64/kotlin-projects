package au.swin.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input1: EditText = findViewById(R.id.valueA)
        val input2: EditText = findViewById(R.id.valueB)
        val mTextView: TextView = findViewById(R.id.answer_box)
        val mButton: Button = findViewById(R.id.button_sum)


        try {
            mButton.setOnClickListener {
                mTextView.textSize = 40f
                val a = Integer.parseInt(input1.text.toString())
                val b = input2.text.toString().toInt()
                mTextView.text = (performAddition(a, b)).toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun performAddition(a: Int, b: Int): Int {
        return a + b
    }
}