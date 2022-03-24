package com.example.first_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var actionBarToggle: ActionBarDrawerToggle;
    private lateinit var navView: NavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * START OF NAVIGATION SECTION
         */
        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0);
        drawerLayout.addDrawerListener(actionBarToggle);

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        actionBarToggle.syncState();

        navView = findViewById(R.id.nav_view);

        navView.setNavigationItemSelectedListener { menuItem ->
            renderFragment(menuItem);
            true;
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        drawerLayout.openDrawer(navView);
        return true;
    }

    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    /**
     * END OF NAVIGATION SECTION
     */

    private fun renderFragment(menuItem: MenuItem) {
        when (menuItem.itemId) {
            R.id.files -> {
                Toast.makeText(this, "Files", Toast.LENGTH_SHORT).show();
                true
            }
            R.id.music -> {
                Toast.makeText(this, "Music", Toast.LENGTH_SHORT).show();
                true
            }
            R.id.connect -> {
                Toast.makeText(this, "Connect", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.print -> {
                Toast.makeText(this, "Print", Toast.LENGTH_SHORT).show()
                true
            }
            R.id.counter -> {
                Toast.makeText(this, "Print", Toast.LENGTH_SHORT).show()
                var intent = Intent(this, CounterActivity::class.java);
                startActivity(intent);
                true;
            }
            R.id.fragment2 -> {
                Toast.makeText(this, "Print", Toast.LENGTH_SHORT).show()
                true;
            }
            else -> {
                false;
            }
        }
    }
}