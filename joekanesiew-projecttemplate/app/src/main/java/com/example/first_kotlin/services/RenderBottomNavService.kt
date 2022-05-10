package com.example.first_kotlin.services

import android.content.Context
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import com.example.first_kotlin.Gallery
import com.example.first_kotlin.Home
import com.example.first_kotlin.Profile
import com.example.first_kotlin.R

class RenderBottomNavService {
    companion object {
        fun setDefaultFragmentView(fragmentManager: FragmentManager) {
            fragmentManager.beginTransaction().apply {
                replace(R.id.mFragmentFrame, Home())
                commit()
            }
        }

        fun renderBottomNav(id: String, context: Context, fragmentManager: FragmentManager) {
            when (id) {
                "Profile" -> {
                    Toast.makeText(context, "Profile Clicked!", Toast.LENGTH_SHORT).show()
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.mFragmentFrame, Profile())
                        commit()
                    }
                }
                "Gallery" -> {
                    Toast.makeText(context, "Gallery Clicked!", Toast.LENGTH_SHORT).show()
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.mFragmentFrame, Gallery())
                        commit()
                    }
                }
                else -> {
                    Toast.makeText(context, "Home Clicked!", Toast.LENGTH_SHORT).show()
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.mFragmentFrame, Home())
                        commit()
                    }
                }
            }
        }
    }
}