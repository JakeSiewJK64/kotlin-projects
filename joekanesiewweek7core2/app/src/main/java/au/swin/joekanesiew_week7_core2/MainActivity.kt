package au.swin.joekanesiew_week7_core2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private var myJapan = ArrayList<Location>()

    private lateinit var shibuyaLinear: ImageView
    private lateinit var tokyoLinear: ImageView
    private lateinit var kyotoLinear: ImageView
    private lateinit var osakaLinear: ImageView

    // creates activity result contract. any data in the inputs from the detail page
    // can be passed back to the main activity
    private val resultContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data: Intent? = it.data
                val updatedLocation = data?.getParcelableExtra<Location>("UPDATED_LOCATION")
                updatedLocation?.let { res ->
                    if (res.toString() != myJapan[res.locationId].toString()) {
                        myJapan[res.locationId] = res
                        Snackbar.make(
                            findViewById(R.id.mainActivityConstraintLayout),
                            "Saved changes successfully!",
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
                updateView()
            }
        }

    // updates the view data
    private fun updateView() {
        findViewById<TextView>(R.id.shibuyaText).text = myJapan[0].locationName
        findViewById<TextView>(R.id.tokyoText).text = myJapan[1].locationName
        findViewById<TextView>(R.id.kyotoText).text = myJapan[2].locationName
        findViewById<TextView>(R.id.osakaText).text = myJapan[3].locationName

        findViewById<TextView>(R.id.shibuyaType).text = myJapan[0].locationType
        findViewById<TextView>(R.id.tokyoType).text = myJapan[1].locationType
        findViewById<TextView>(R.id.kyotoType).text = myJapan[2].locationType
        findViewById<TextView>(R.id.osakaType).text = myJapan[3].locationType

        findViewById<RatingBar>(R.id.shibuyaRating).rating = myJapan[0].locationRating
        findViewById<RatingBar>(R.id.tokyoRating).rating = myJapan[1].locationRating
        findViewById<RatingBar>(R.id.kyotoRating).rating = myJapan[2].locationRating
        findViewById<RatingBar>(R.id.osakaRating).rating = myJapan[3].locationRating

        shibuyaLinear.setImageResource(myJapan[0].locationImage)
        tokyoLinear.setImageResource(myJapan[1].locationImage)
        kyotoLinear.setImageResource(myJapan[2].locationImage)
        osakaLinear.setImageResource(myJapan[3].locationImage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        shibuyaLinear = findViewById(R.id.shibuyaImage)
        tokyoLinear = findViewById(R.id.tokyoImage)
        kyotoLinear = findViewById(R.id.kyotoImage)
        osakaLinear = findViewById(R.id.osakaImage)

        Log.i("SOMETHING_HAPPENED", "ON CREATE CALLED")

        // checks if the array is empty, if so, populate it with data
        if (myJapan.isEmpty()) {
            Log.i("SOMETHING_HAPPENED", "ASSIGNED VALUES TO MYJAPAN")
            myJapan.apply {
                add(
                    Location(
                        0,
                        resources.getString(R.string.shibuya),
                        5f,
                        resources.getString(R.string.sample_date),
                        resources.getString(R.string.city_type),
                        R.drawable.shibuya,
                        true
                    )
                )
                add(
                    Location(
                        1,
                        resources.getString(R.string.tokyo),
                        4f,
                        resources.getString(R.string.sample_date),
                        resources.getString(R.string.city_type),
                        R.drawable.tokyo,
                        true
                    )
                )
                add(
                    Location(
                        2,
                        resources.getString(R.string.kyoto),
                        3f,
                        resources.getString(R.string.sample_date),
                        resources.getString(R.string.city_type),
                        R.drawable.kyoto,
                        false
                    )
                )
                add(
                    Location(
                        3,
                        resources.getString(R.string.osaka),
                        2f,
                        resources.getString(R.string.sample_date),
                        resources.getString(R.string.city_type),
                        R.drawable.osaka,
                        false
                    )
                )
            }
            updateView()
        }

        // on click listener to detect user click interaction
        shibuyaLinear.setOnClickListener {
            initiateActivity(myJapan[0])
        }

        tokyoLinear.setOnClickListener {
            initiateActivity(myJapan[1])
        }

        kyotoLinear.setOnClickListener {
            initiateActivity(myJapan[2])
        }

        osakaLinear.setOnClickListener {
            initiateActivity(myJapan[3])
        }
    }

    // initiates the activity using intent.
    // putExtra method is used to store location data class object into LOCATION_JAPAN key string.
    private fun initiateActivity(mLocation: Location) {
        val i = Intent(this, LocationDetail::class.java)
        i.putExtra("LOCATION_JAPAN", mLocation)
        resultContract.launch(i)
    }
}