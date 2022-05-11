package au.swin.joekanesiew_week7_core2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var locationRecyclerView: RecyclerView
    private lateinit var locationArrayList: ArrayList<Location>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationArrayList = ArrayList()
        locationRecyclerView = findViewById(R.id.locationRecyclerView)
        locationArrayList.add(Location("Shibuya", 5, "5/5/2022", "City", R.drawable.shibuya))
        locationArrayList.add(Location("Tokyo", 4, "6/5/2022", "City", R.drawable.tokyo))
        locationArrayList.add(Location("Kyoto", 3, "7/5/2022", "City", R.drawable.kyoto))
        locationArrayList.add(Location("Osaka", 2, "8/5/2022", "City", R.drawable.osaka))

        val locationAdapter = LocationAdapter(this, locationArrayList)
        val layoutManager = GridLayoutManager(this, 2)
        locationRecyclerView.layoutManager = layoutManager
        locationRecyclerView.adapter = locationAdapter
    }
}