package com.example.first_kotlin.services

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.first_kotlin.*

class RenderDrawerFragmentService : Activity() {
    companion object {
        fun renderDrawerFragmentService(menuItem: MenuItem, context: Context) {
            when (menuItem.itemId) {
                R.id.files -> {
                    Toast.makeText(context, "Files", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.music -> {
                    Toast.makeText(context, "Music", Toast.LENGTH_SHORT).show()
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
                    val intent = Intent(context, CounterActivity::class.java)
                    startActivity(context, intent, null)
                    true
                }
                R.id.fragment2 -> {
                    Toast.makeText(context, "Fragment2", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.garden_recycler_activity -> {
                    Toast.makeText(context, "Garden", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, Flower::class.java)
                    startActivity(context, intent, null)
                }
                R.id.genshin_recycler_activity -> {
                    Toast.makeText(context, "Genshin", Toast.LENGTH_SHORT).show()
                    val intent = Intent(context, GenshinRecyclerView::class.java)
                    startActivity(context, intent, null)
                }
                R.id.users_recyclerview -> {
                    Toast.makeText(context, "Genshin", Toast.LENGTH_SHORT).show()
                    val intent  = Intent(context, UserRecyclerView::class.java)
                    startActivity(context, intent, null)
                }
                else -> {
                }
            }
        }
    }
}