package com.example.first_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.adapter.GenshinAdapter
import com.example.first_kotlin.repository.GenshinRepository
import kotlinx.android.synthetic.main.activity_genshin_recycler_view.*

class GenshinRecyclerView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genshin_recycler_view)

        val genshinList: Array<String> = GenshinRepository(this).getGenshinList()
        val recyclerView:RecyclerView = genshin_recycler_view
        recyclerView.adapter = GenshinAdapter(genshinList)
    }
}