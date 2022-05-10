package com.example.first_kotlin.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.R

class GenshinViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.genshin_viewholder)
    fun bind(name: String) {
        textView.text = name
    }
}