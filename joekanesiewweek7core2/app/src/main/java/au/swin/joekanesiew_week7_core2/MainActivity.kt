package au.swin.joekanesiew_week7_core2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    private var myJapan = ArrayList<Location>()

    private val resultContract =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                val data: Intent? = it.data
                val updatedLocation = data?.getParcelableExtra<Location>("UPDATED_LOCATION")
                updatedLocation?.let { res ->
                    myJapan.set(res.locationId, res)
                }
                updateView()
            }
        }
    
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

        findViewById<ImageView>(R.id.shibuyaImage).setImageResource(myJapan[0].locationImage)
        findViewById<ImageView>(R.id.tokyoImage).setImageResource(myJapan[1].locationImage)
        findViewById<ImageView>(R.id.kyotoImage).setImageResource(myJapan[2].locationImage)
        findViewById<ImageView>(R.id.osakaImage).setImageResource(myJapan[3].locationImage)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.i("SOMETHING_HAPPENED", "ON CREATE CALLED")

        if (myJapan.isEmpty()) {
            Log.i("SOMETHING_HAPPENED", "ASSIGNED VALUES TO MYJAPAN")
            myJapan.add(Location(0, "Shibuya", 5f, "05/05/2022", "City", R.drawable.shibuya, true))
            myJapan.add(Location(1, "Tokyo", 4f, "06/05/2022", "City", R.drawable.tokyo, true))
            myJapan.add(Location(2, "Kyoto", 3f, "30/05/2022", "City", R.drawable.kyoto, false))
            myJapan.add(Location(3, "Osaka", 2f, "10/05/2022", "City", R.drawable.osaka, false))
            updateView()
        }

        val shibuyaLinear: LinearLayout = findViewById(R.id.shibuyaLayout)
        val tokyoLinear: LinearLayout = findViewById(R.id.tokyoLayout)
        val kyotoLinear: LinearLayout = findViewById(R.id.kyotoLayout)
        val osakaLinear: LinearLayout = findViewById(R.id.osakaLayout)

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

    private fun initiateActivity(mLocation: Location) {
        val i = Intent(this, LocationDetail::class.java)
        i.putExtra("LOCATION_JAPAN", mLocation)
        resultContract.launch(i)
    }
}