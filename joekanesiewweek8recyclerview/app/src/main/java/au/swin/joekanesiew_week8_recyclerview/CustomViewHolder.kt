package au.swin.joekanesiew_week8_recyclerview

import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val nameText = itemView.findViewById<TextView>(R.id.nameText)
    val descText = itemView.findViewById<TextView>(R.id.descriptionText)
    val imageView = itemView.findViewById<ImageView>(R.id.mImageView)

    init {
        itemView.setOnClickListener {
            val position: Int = adapterPosition
            val intent = Intent(it.context, DetailActivity::class.java)
            intent.putExtra("NAME", nameText.text.toString())
            it.context.startActivity(intent)
            Snackbar.make(view, "You have selected ${nameText.text}\nCat number ${position + 1}", Snackbar.LENGTH_LONG).show()
        }
    }
}