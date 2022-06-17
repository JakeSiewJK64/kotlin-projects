package au.swin.joekanesiew_core3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MedalistHistory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medalist_history)

        // retrieve last clicked medalist from shared preferneces
        val sharedPref = getSharedPreferences("[DATA]:PREVIOUS_MEDALIST", MODE_PRIVATE)
        val medalistTextView: TextView = findViewById(R.id.medalistDetailsName)
        val mString = String.format(
            resources.getString(R.string.medalist_details_name),
            sharedPref.getString("LAST_CLICKED_COUNTRY_NAME", null),
            sharedPref.getString("LAST_CLICKED_COUNTRY_IOC", null),
        )
        medalistTextView.text = mString
    }
}