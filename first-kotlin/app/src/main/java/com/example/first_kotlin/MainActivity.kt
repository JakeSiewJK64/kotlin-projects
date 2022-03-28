package com.example.first_kotlin

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.first_kotlin.services.RenderBottomNavService
import com.example.first_kotlin.services.RenderDrawerFragmentService
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.bottom_navbar.*
import kotlinx.android.synthetic.main.top_app_bar.*

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout;
    private lateinit var actionBarToggle: ActionBarDrawerToggle;
    private lateinit var navView: NavigationView;

    private lateinit var bottomNavigation: BottomNavigationView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = bottom_navbar;
        bottomNavigation.setOnItemSelectedListener { i ->
            RenderBottomNavService.renderBottomNav(i.toString(), this)
            true;
        }

        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.close()
            } else {
                drawerLayout.openDrawer(navView)
            }
        }

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
            RenderDrawerFragmentService.renderDrawerFragmentService(menuItem, this);
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
}