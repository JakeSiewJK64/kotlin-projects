package au.swin.joekanesiew_week7_core2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class LocationDetail : AppCompatActivity() {

    private lateinit var locationImageView: ImageView
    private lateinit var locationTypeTextView: TextView
    private lateinit var locationRatingRating: RatingBar
    private lateinit var locationDateTextView: TextView
    private lateinit var locationNameTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        locationImageView = findViewById(R.id.locationDetailImage)
        locationTypeTextView = findViewById(R.id.locationDetailType)
        locationDateTextView = findViewById(R.id.locationDetailDate)
        locationRatingRating = findViewById(R.id.locationDetailRatingBar)
        locationNameTextView = findViewById(R.id.locationDetailName)

        intent.getParcelableExtra<Location>("locationData")?.let {
            locationImageView.setImageResource(it.locationImage)
            locationTypeTextView.text = it.locationType
            locationRatingRating.rating = it.locationRating.toString().toFloat()
            locationDateTextView.text = it.locationDate
            locationNameTextView.text = it.locationName
            Snackbar.make(locationImageView, "Loaded ${it.locationName} Successfully!", Snackbar.LENGTH_LONG).setAction("OK") {
            }.show()
        }
    }
}