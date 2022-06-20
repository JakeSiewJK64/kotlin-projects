package au.swin.joekanesiew_customapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import au.swin.joekanesiew_customapp.fragments.EventLogFragment
import au.swin.joekanesiew_customapp.fragments.MainFragment
import au.swin.joekanesiew_customapp.fragments.NewRecipeFragment
import au.swin.joekanesiew_customapp.fragments.RecipeListFragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.top_app_bar.*

class MainActivity : AppCompatActivity() {

    private lateinit var navView: NavigationView
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    private fun replaceFragment(myCustomView: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.recipeFragmentFrame, myCustomView)
            commit()
        }
        drawerLayout.close()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.recipeFragmentFrame, MainFragment())
            commit()
        }

        drawerLayout = findViewById(R.id.recipeDrawerLayout)
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        navView = findViewById(R.id.recipeNavView)

        toolbar.setNavigationOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.close()
            } else {
                drawerLayout.openDrawer(navView)
            }
        }

        drawerLayout.addDrawerListener(actionBarToggle)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.newRecipe -> {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        resources.getString(R.string.launchingRecipeEditor),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("OK") {}.show()
                    replaceFragment(NewRecipeFragment())
                }
                R.id.viewRecipe -> {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        resources.getString(R.string.launchingRecipeList),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(resources.getString(R.string.okDialog)) {}.show()
                    replaceFragment(RecipeListFragment())
                }
                R.id.viewEventLog -> {
                    Snackbar.make(
                        findViewById(android.R.id.content),
                        resources.getString(R.string.launchingEventLogs),
                        Snackbar.LENGTH_LONG
                    )
                        .setAction(resources.getString(R.string.okDialog)) {}.show()
                    replaceFragment(EventLogFragment())
                }
                else -> {
                    replaceFragment(MainFragment())
                }
            }
            true
        }
    }
}