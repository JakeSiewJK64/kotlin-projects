package com.example.first_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.R
import com.example.first_kotlin.viewHolder.FlowerViewHolder;

class FlowerAdapter(private val flowerList: Array<String>) :
    RecyclerView.Adapter<FlowerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.flower_viewholder, parent, false);
        return FlowerViewHolder(view);
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.bind(flowerList[position]);
    }

    override fun getItemCount(): Int {
        return flowerList.size;
    }
}