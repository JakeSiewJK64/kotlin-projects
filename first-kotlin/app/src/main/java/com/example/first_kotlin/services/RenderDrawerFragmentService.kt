package com.example.first_kotlin.services

import android.content.Context
import android.view.MenuItem
import android.widget.Toast
import com.example.first_kotlin.R

class RenderDrawerFragmentService {
    companion object {
        fun renderDrawerFragmentService(menuItem: MenuItem, context: Context) {
            when (menuItem.itemId) {
                R.id.files -> {
                    Toast.makeText(context, "Files", Toast.LENGTH_SHORT).show();
                    true
                }
                R.id.music -> {
                    Toast.makeText(context, "Music", Toast.LENGTH_SHORT).show();
                    true
                }
                R.id.connect -> {
                    Toast.makeText(context, "Connect", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.print -> {
                    Toast.makeText(context, "Print", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.counter -> {
                    Toast.makeText(context, "Counter", Toast.LENGTH_SHORT).show()
                    true;
                }
                R.id.fragment2 -> {
                    Toast.makeText(context, "Fragment2", Toast.LENGTH_SHORT).show()
                    true;
                }
                else -> {
                    false;
                }
            }
        }
    }
}