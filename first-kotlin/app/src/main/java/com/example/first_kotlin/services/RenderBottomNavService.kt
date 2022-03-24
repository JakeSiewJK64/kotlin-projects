package com.example.first_kotlin.services

import android.content.Context
import android.widget.Toast

class RenderBottomNavService {

    companion object {
        fun renderBottomNav(id: String, context: Context) {
            when (id) {
                "Profile" -> Toast.makeText(context, "Profile Clicked!", Toast.LENGTH_SHORT).show();
                "Gallery" -> Toast.makeText(context, "Gallery Clicked!", Toast.LENGTH_SHORT).show();
                "Home" -> Toast.makeText(context, "Home Clicked!", Toast.LENGTH_SHORT).show();
                else -> false;
            }
        }
    }

    private constructor() {
    }

}