package au.swin.mjetpackviewmodelapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import com.google.android.material.snackbar.Snackbar

class ViewModelClass : ViewModel() {
    var answer = 0
    var colorCode = "#FFFFFF"
    fun showResult(num1: String) {
        answer = num1.toInt() * 2
    }

    fun showColor(): String {
        colorCode = when(answer) {
            0 -> "#000000"
            in 1..28 -> "#00FF00"
            else -> "#FF0000"
        }
        return colorCode
    }
}

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mEditView: EditText = findViewById(R.id.EditText)
        val mTextView: TextView = findViewById(R.id.TextView)
        val mButton: Button = findViewById(R.id.Button)
        val mViewModel: ViewModelClass by viewModels()

        mTextView.text = mViewModel.answer.toString()
        mTextView.setTextColor(Color.parseColor(mViewModel.colorCode))

        mButton.setOnClickListener{
            if(TextUtils.isEmpty(mEditView.text.toString())) {
                Snackbar.make(mTextView, "Error Processing Answer or Color code", Snackbar.LENGTH_LONG).show()
            } else {
                mViewModel.showResult(mEditView.text.toString())
                mTextView.text = mViewModel.answer.toString()
                mViewModel.showColor()
                mTextView.setTextColor(Color.parseColor(mViewModel.colorCode))
            }
        }
    }
}