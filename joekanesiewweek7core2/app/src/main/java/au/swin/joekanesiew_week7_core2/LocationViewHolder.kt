package au.swin.joekanesiew_week7_core2

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LocationViewHolder(view: View) : RecyclerView.ViewHolder(view){
    private val locationImageView: ImageView = view.findViewById(R.id.locationImage)
    private val locationNameTextView: TextView = view.findViewById(R.id.locationNameText)
    private val locationRatingTextView: TextView = view.findViewById(R.id.locationRatingText)
    private val locationDateTextView: TextView = view.findViewById(R.id.locationDate)
    private val locationTypeTextView: TextView = view.findViewById(R.id.locationType)

    fun bind(locationName: String, locationRating: Int, locationType: String, locationImage: Int, locationDate: String) {
        locationNameTextView.text = locationName
        locationDateTextView.text = locationDate
        locationTypeTextView.text = locationType
        locationRatingTextView.text = locationRating.toString()
        locationImageView.setImageResource(locationImage)
    }
}