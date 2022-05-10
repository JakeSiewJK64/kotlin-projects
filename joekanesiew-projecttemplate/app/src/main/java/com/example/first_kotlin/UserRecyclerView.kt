package com.example.first_kotlin

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.adapter.UsersAdapter
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import models.UserModel

class UserRecyclerView : AppCompatActivity() {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: UsersAdapter
    private lateinit var mUserArray: ArrayList<UserModel>
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_recycler_view)

        mUserArray = arrayListOf()
        mRecyclerView = findViewById(R.id.custom_users_recyclerview)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mAdapter = UsersAdapter(mUserArray)
        mRecyclerView.adapter = mAdapter

        eventChangeListener()
    }

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()

        db.collection("mUsers")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    Log.e("ERROR", error.message.toString())
                }

                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        mUserArray.add(dc.document.toObject(UserModel::class.java))
                    }
                }
                mAdapter.notifyDataSetChanged()
            }
    }
}