package au.swin.joekanesiew_week8_recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CustomAdapter : RecyclerView.Adapter<CustomViewHolder>() {

    private val catName = arrayListOf(
        "Cat 1",
        "Cat 2",
        "Cat 3",
        "Cat 1",
        "Cat 2",
        "Cat 3",
        "Cat 1",
        "Cat 2",
        "Cat 3",
        "Cat 1",
        "Cat 2",
        "Cat 3"
    )
    private val catImg = arrayListOf(
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.a,
        R.drawable.b,
        R.drawable.c,
        R.drawable.a,
        R.drawable.b,
        R.drawable.c
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val cellForRow = layoutInflater.inflate(R.layout.viewmodel, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.nameText.text = catName[position].toString()
        holder.descText.text = position.toString()
        holder.imageView.setImageResource(catImg[position])
    }

    override fun getItemCount(): Int {
        return catName.size
    }

}