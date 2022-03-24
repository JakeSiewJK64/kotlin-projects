package com.example.first_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.first_kotlin.adapter.FlowerAdapter
import com.example.first_kotlin.repository.FlowerDatasource

class Flower : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flower);

        val flowerList = FlowerDatasource(this).getFlowerList();
        val recyclerView: RecyclerView = findViewById(R.id.flower_recycler);
        recyclerView.adapter = FlowerAdapter(flowerList);
    }
}