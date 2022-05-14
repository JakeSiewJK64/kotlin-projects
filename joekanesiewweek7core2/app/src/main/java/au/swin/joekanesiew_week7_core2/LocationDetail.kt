package au.swin.joekanesiew_week7_core2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class LocationDetail : AppCompatActivity() {

    private lateinit var locationImageView: ImageView
    private lateinit var locationRatingRating: RatingBar
    private lateinit var locationFavorite: CheckBox
    private lateinit var locationLinearLayout: LinearLayout

    private lateinit var locationNameInputEditText: TextInputLayout
    private lateinit var locationDateInputEditText: TextInputLayout
    private lateinit var locationTypeInputEditText: TextInputLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        locationLinearLayout = findViewById(R.id.locationDetailLayout)
        locationImageView = findViewById(R.id.locationDetailImage)
        locationRatingRating = findViewById(R.id.locationDetailRatingBar)
        locationFavorite = findViewById(R.id.favoriteLocationCheckbox)

        locationNameInputEditText = findViewById(R.id.locationNameEditTextLayout)
        locationDateInputEditText = findViewById(R.id.locationDateEditTextLayout)
        locationTypeInputEditText = findViewById(R.id.locationTypeEditTextLayout)

        intent.getParcelableExtra<Location>("locationData")?.let {
            locationImageView.setImageResource(it.locationImage)
            locationNameInputEditText.editText!!.setText(it.locationName)
            locationTypeInputEditText.editText!!.setText(it.locationType)
            locationDateInputEditText.editText!!.setText(it.locationDate)
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