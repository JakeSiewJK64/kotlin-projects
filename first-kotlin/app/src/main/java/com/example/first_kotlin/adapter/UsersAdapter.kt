package com.example.first_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.R
import com.example.first_kotlin.viewHolder.UserViewHolder
import models.UserModel

class UsersAdapter(private val mUserList: ArrayList<UserModel>) :
    RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.m_user_view_holder, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(mUserList[position].mUserName.toString())
    }

    override fun getItemCount(): Int {
        return mUserList.size
    }
}