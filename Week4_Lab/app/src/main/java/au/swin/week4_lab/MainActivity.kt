package au.swin.week4_lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mButton1: Button = findViewById(R.id.mButton1)
        val mButton2: Button = findViewById(R.id.mButton2)
        val mButton3: Button = findViewById(R.id.mButton3)
        val mImageView: ImageView = findViewById(R.id.mImageView)
        val mTextView: TextView = findViewById(R.id.mTextView)

        mButton1.setOnClickListener {
            mTextView.text = "This is Spectre"
            mImageView.setImageResource(R.drawable.oct)
        }

        mButton2.setOnClickListener {
            mTextView.text = "This is Rani"
            mImageView.setImageResource(R.drawable.rani)
        }

        mButton3.setOnClickListener {
            mTextView.text = "This is Texas"
            mImageView.setImageResource(R.drawable.texas)
        }
    }
}