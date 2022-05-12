package au.swin.joekanesiew_week7_core2

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView

class LocationAdapter(private val context: Context, private val locationList: ArrayList<Location>) :
    RecyclerView.Adapter<LocationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.location_view_holder, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        locationList[position].let {
            holder.bind(
                it.locationName,
                it.locationRating,
                it.locationType,
                it.locationImage,
                it.locationDate,
            )
            holder.itemView.setOnClickListener {
                val mIntent = Intent(context, LocationDetail::class.java)
                mIntent.putExtra("locationData", locationList[position])
                startActivity(context, mIntent, null)
            }
        }
    }

    override fun getItemCount(): Int {
        return locationList.size
    }
}