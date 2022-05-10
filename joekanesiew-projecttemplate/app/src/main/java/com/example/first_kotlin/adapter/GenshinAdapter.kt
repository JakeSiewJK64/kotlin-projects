package com.example.first_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.R
import com.example.first_kotlin.viewHolder.GenshinViewHolder

class GenshinAdapter(private val genshinList: Array<String>) :
    RecyclerView.Adapter<GenshinViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenshinViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.genshin_viewholder, parent, false)
        return GenshinViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenshinViewHolder, position: Int) {
        holder.bind(genshinList[position])
    }

    override fun getItemCount(): Int {
        return genshinList.size
    }
}