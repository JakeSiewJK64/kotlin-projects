package au.swin.joekanesiew_core3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MedalistDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medalist_history)

        // retrieve last clicked medalist from shared preferences
        val sharedPref = getSharedPreferences(MedalistContants.MEDALLISTS_SHARE_PREFS, MODE_PRIVATE)
        val medalistTextView: TextView = findViewById(R.id.medalistDetailsName)

        // retrieves the values from share pref file.
        val shareName: String =
            sharedPref.getString("[DATA]:PREVIOUS_MEDALIST_NAME", null).toString()
        val shareIOC: String = sharedPref.getString("[DATA]:PREVIOUS_MEDALIST_IOC", null).toString()

        // checks if the share pref value is null.
        // if not null, set the TextViews text to each respective values.
        var mString = "No countries selected yet!"
        if (shareIOC != "null" && shareName != "null") {
            mString = String.format(
                resources.getString(R.string.medalist_details_name),
                shareName,
                shareIOC
            )
        }
        medalistTextView.text = mString
    }
}