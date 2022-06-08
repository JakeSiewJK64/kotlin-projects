package au.swin.joekanesiew_core3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MedalistHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medalist_history)
        val sharedPref = getSharedPreferences("LastClickedMedalist", MODE_PRIVATE)
        val medalistTextView: TextView = findViewById(R.id.medalistDetailsName)
        val mString = resources.getString(R.string.medalist_details_name) + " " + sharedPref.getString("LAST_CLICKED", null)
        medalistTextView.text = mString
    }
}