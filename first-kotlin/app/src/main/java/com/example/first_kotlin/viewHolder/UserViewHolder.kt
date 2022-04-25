package com.example.first_kotlin.viewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.R

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val mTextView: TextView = itemView.findViewById(R.id.mUserTextView)
    fun bind(mText: String) {
        mTextView.text = mText
    }
}