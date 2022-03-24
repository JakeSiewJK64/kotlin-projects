package com.example.first_kotlin.repository

import android.content.Context
import com.example.first_kotlin.R

class FlowerDatasource(private val context: Context) {
    fun getFlowerList(): Array<String> {
        return context.resources.getStringArray(R.array.flower_array);
    }
}