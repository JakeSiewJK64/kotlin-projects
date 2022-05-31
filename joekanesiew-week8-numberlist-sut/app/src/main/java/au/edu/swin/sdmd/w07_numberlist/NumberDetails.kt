package au.edu.swin.sdmd.w07_numberlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class NumberDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_details)

        val mNumber = intent.getStringExtra("MY_NUMBER").toString()
        val mNumberTextView = findViewById<TextView>(R.id.mNumber)
        val mNumberDescTextView = findViewById<TextView>(R.id.mNumberDesc)
        Log.i("NUMBER", mNumber)
        mNumberTextView.text = mNumber
        if (mNumber.toInt() % 2 == 0) {
            mNumberDescTextView.text = "Number is Even"
        } else {
            mNumberDescTextView.text = "Number is Odd"
        }
    }
}