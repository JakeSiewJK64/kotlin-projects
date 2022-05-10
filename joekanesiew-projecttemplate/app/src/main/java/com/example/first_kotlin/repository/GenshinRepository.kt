package com.example.first_kotlin.repository

import android.content.Context
import com.example.first_kotlin.R

class GenshinRepository(private var context: Context) {
    fun getGenshinList(): Array<String> {
        return context.resources.getStringArray(R.array.genshin_array)
    }
}