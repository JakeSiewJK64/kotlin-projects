package au.swin.joekanesiew_week7_core2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class LocationDetail : AppCompatActivity() {

    private lateinit var locationImageView: ImageView
    private lateinit var locationRatingRating: RatingBar
    private lateinit var locationTypeTextView: TextInputEditText
    private lateinit var locationDateTextView: TextInputEditText
    private lateinit var locationNameTextView: TextInputEditText
    private lateinit var locationFavorite: CheckBox
    private lateinit var locationLinearLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        locationLinearLayout = findViewById(R.id.locationDetailLayout)
        locationImageView = findViewById(R.id.locationDetailImage)
        locationTypeTextView = findViewById(R.id.locationDetailType)
        locationDateTextView = findViewById(R.id.locationDetailDate)
        locationRatingRating = findViewById(R.id.locationDetailRatingBar)
        locationNameTextView = findViewById(R.id.locationDetailName)
        locationFavorite = findViewById(R.id.favoriteLocationCheckbox)

        intent.getParcelableExtra<Location>("locationData")?.let {
            locationImageView.setImageResource(it.locationImage)
            locationTypeTextView.setText(it.locationType)
            locationNameTextView.setText(it.locationName)
            locationDateTextView.setText(it.locationDate)
            locationRatingRating.rating = it.locationRating.toString().toFloat()
            locationFavorite.isChecked = it.favorite
            Snackbar.make(locationLinearLayout, "Loaded ${it.locationName} Successfully!", Snackbar.LENGTH_LONG).setAction("OK") {
            }.show()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()


    }
}