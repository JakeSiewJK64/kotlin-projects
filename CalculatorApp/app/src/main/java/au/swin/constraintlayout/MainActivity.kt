package au.swin.constraintlayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import au.swin.constraintlayout.Constants.CalcMode
import com.google.android.material.snackbar.Snackbar
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val input1: EditText = findViewById(R.id.valueA)
        val input2: EditText = findViewById(R.id.valueB)
        val mTextView: TextView = findViewById(R.id.answer_box)
        val mButton: Button = findViewById(R.id.button_sum)

        val addRadio: RadioButton = findViewById(R.id.add_radio)
        val multRadio: RadioButton = findViewById(R.id.mult_radio)
        var mode = CalcMode.ADDITION

        try {

            addRadio.setOnClickListener {
                mode = CalcMode.ADDITION
                Snackbar.make(mTextView, "Switched to Addition", Snackbar.LENGTH_LONG).setAction("OK") {
                    print("message")
                }.show()
                mButton.text = getString(R.string.addition_label)
            }

            multRadio.setOnClickListener {
                mode = CalcMode.MULTIPLICATION
                Snackbar.make(mTextView, "Switched to Multiply", Snackbar.LENGTH_LONG).setAction("OK") {
                    print("message")
                }.show()
                mButton.text = getString(R.string.multiply_label)
            }

            mButton.setOnClickListener {
                mTextView.textSize = 40f
                val a = Integer.parseInt(input1.text.toString())
                val b = input2.text.toString().toInt()

                if (mode.equals(CalcMode.MULTIPLICATION)) {
                    mTextView.text = (performMultiplcation(a, b)).toString()
                } else if (mode.equals(CalcMode.ADDITION)) {
                    mTextView.text = (performAddition(a, b)).toString()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun performMultiplcation(a: Int, b: Int): Int {
        return a * b
    }

    fun performAddition(a: Int, b: Int): Int {
        return a + b
    }
}