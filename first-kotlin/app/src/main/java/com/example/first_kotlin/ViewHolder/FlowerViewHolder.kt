package com.example.first_kotlin.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.R

class FlowerViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val flowerTextView: TextView = itemView.findViewById(R.id.flower_text);
    fun bind(word: String) {
        flowerTextView.text = word;
    }
}