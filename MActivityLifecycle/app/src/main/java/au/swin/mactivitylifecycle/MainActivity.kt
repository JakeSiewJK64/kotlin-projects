package au.swin.mactivitylifecycle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    var answer: Int = 0
    var colorCode: String = "#000"

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("COLOR_CODE", colorCode)
        outState.putInt("ANSWER_CODE", answer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("LIFECYCLE", "onCreate ran")

        val mEditView: EditText = findViewById(R.id.EditText)
        val mTextView: TextView = findViewById(R.id.TextView)
        val mButton: Button = findViewById(R.id.Button)

        if (savedInstanceState != null) {
            Log.i("LIFECYCLE", savedInstanceState.getString("COLOR_CODE").toString())
            mTextView.setTextColor(Color.parseColor(savedInstanceState.getString("COLOR_CODE")))
            mTextView.text = savedInstanceState.getInt("ANSWER_CODE", 123).toString()
        }

        mButton.setOnClickListener {
            if (!TextUtils.isEmpty(mEditView.text.toString())) {
                answer = mEditView.text.toString().toInt() * 2
                mTextView.text = answer.toString()
                colorCode = "#FF0000"
                mTextView.setTextColor(Color.parseColor(colorCode))
                savedInstanceState?.putInt("ANSWER_CODE", answer)
            } else {
                Snackbar.make(mTextView, "Error", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("LIFECYCLE", "onStart ran")
    }

    override fun onResume() {
        super.onResume()
        Log.i("LIFECYCLE", "onResume ran")
    }

    override fun onPause() {
        super.onPause()
        Log.i("LIFECYCLE", "onPause ran")
    }

    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "onStop ran")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("LIFECYCLE", "onDestroy ran")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i("LIFECYCLE", "onRestart ran")
    }
}