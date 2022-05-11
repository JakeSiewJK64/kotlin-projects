package au.swin.joekanesiew_week7_core2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class LocationDetail : AppCompatActivity() {

    private lateinit var locationImageView: ImageView
    private lateinit var locationTypeTextView: TextView
    private lateinit var locationRatingTextView: TextView
    private lateinit var locationDateTextView: TextView
    private lateinit var locationNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        locationImageView = findViewById(R.id.locationDetailImage)
        locationTypeTextView = findViewById(R.id.locationDetailType)
        locationDateTextView = findViewById(R.id.locationDetailDate)
        locationRatingTextView = findViewById(R.id.locationDetailRating)
        locationNameTextView = findViewById(R.id.locationDetailName)

        intent.getParcelableExtra<Location>("locationData")?.let {
            locationImageView.setImageResource(it.locationImage)
            locationTypeTextView.text = it.locationType
            locationRatingTextView.text = it.locationRating.toString()
            locationDateTextView.text = it.locationDate
            locationNameTextView.text = it.locationName
            Snackbar.make(locationImageView, "Loaded ${it.locationName} Successfully!", Snackbar.LENGTH_LONG).setAction("OK") {
            }.show()
        }
    }
}