package au.swin.joekanesiew_week7_core2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class LocationDetail : AppCompatActivity() {

    private lateinit var locationImageView: ImageView
    private lateinit var locationRating: RatingBar
    private lateinit var locationFavorite: CheckBox
    private lateinit var locationLinearLayout: LinearLayout

    private lateinit var locationNameInputEditText: TextInputLayout
    private lateinit var locationDateInputEditText: TextInputLayout
    private lateinit var locationTypeInputEditText: TextInputLayout

    private var locationId = 0
    private var locationImageResourceId = 0

    private fun displaySnackbar(message: String) {
        Snackbar.make(findViewById(R.id.locationDetailLayout), message, Snackbar.LENGTH_LONG)
            .setAction("OK") {
            }
            .show()
    }

    // validates the input of the edit text
    private fun validateInput(): Boolean {
        val lDate: String = locationDateInputEditText.editText?.text.toString()
        val lName: String = locationNameInputEditText.editText?.text.toString()
        val lType: String = locationTypeInputEditText.editText?.text.toString()

        if (!lName.matches(Regex(pattern = "[A-Za-z]+"))) {
            displaySnackbar(resources.getString(R.string.snackbar_invalidName))
            locationNameInputEditText.error = resources.getString(R.string.invalidName)
            return false
        } else if (!lType.matches(Regex(pattern = "[A-Za-z]+"))) {
            displaySnackbar(resources.getString(R.string.snackbar_invalidType))
            locationTypeInputEditText.error = resources.getString(R.string.invalidType)
            return false
        } else if (!lDate.matches(Regex(pattern = """([0][1-9]|[1-2][0-9]|[3][0-1])/([0][1-9]|[1][0-2])/[0-2][0][2][0-9]+"""))) {
            displaySnackbar(resources.getString(R.string.snackbar_invalidDate))
            locationDateInputEditText.error = resources.getString(R.string.invalidDate)
            return false
        }
        return true
    }

    // when back button is pressed, validateInput method is called to check validity of data entered.
    // if not valid, dont proceed back to main activity.
    // if valid, store the updated data into UPDATED_LOCATION key string using putExtra.
    // use setResult  instead of startActivity method and pass in the status and intent.
    override fun onBackPressed() {
        if (validateInput()) {
            val updatedLocation = Location(
                locationName = locationNameInputEditText.editText?.text.toString(),
                locationType = locationTypeInputEditText.editText?.text.toString(),
                locationDate = locationDateInputEditText.editText?.text.toString(),
                locationRating = locationRating.rating,
                favorite = locationFavorite.isChecked,
                locationId = locationId,
                locationImage = locationImageResourceId
            )
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("UPDATED_LOCATION", updatedLocation)
            setResult(RESULT_OK, i)
            super.onBackPressed()
        } else {
            return
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_detail)

        locationLinearLayout = findViewById(R.id.locationDetailLayout)
        locationImageView = findViewById(R.id.locationDetailImage)
        locationRating = findViewById(R.id.locationDetailRatingBar)
        locationFavorite = findViewById(R.id.favoriteLocationCheckbox)

        locationNameInputEditText = findViewById(R.id.locationNameEditTextLayout)
        locationDateInputEditText = findViewById(R.id.locationDateEditTextLayout)
        locationTypeInputEditText = findViewById(R.id.locationTypeEditTextLayout)

        // getParcelableExtra method is used to fetch data from the intent.
        // ? question mark is used because data could contain null values.
        intent.getParcelableExtra<Location>("LOCATION_JAPAN")?.let {
            locationImageView.setImageResource(it.locationImage)
            locationNameInputEditText.editText?.setText(it.locationName)
            locationTypeInputEditText.editText?.setText(it.locationType)
            locationDateInputEditText.editText?.setText(it.locationDate)
            locationRating.rating = it.locationRating.toString().toFloat()
            locationFavorite.isChecked = it.favorite

            locationId = it.locationId
            locationImageResourceId = it.locationImage

            Snackbar.make(
                locationLinearLayout,
                "Loaded ${it.locationName} Successfully!",
                Snackbar.LENGTH_LONG
            ).setAction("OK") {
            }.show()
        }
    }
}