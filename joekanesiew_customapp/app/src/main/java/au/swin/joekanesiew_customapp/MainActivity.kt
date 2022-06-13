package au.swin.joekanesiew_customapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.top_app_bar.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recipeList: ArrayList<Recipe>

    private lateinit var navView: NavigationView
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var drawerLayout: DrawerLayout

    private fun getRecipeData() {
        recipeList = ArrayList()
        db = FirebaseFirestore.getInstance()
        db.collection("joekanesiew-recipe").addSnapshotListener { value, err ->
            if (err != null) {
                Log.e("ERROR", err.message.toString())
            }

            for (dc: DocumentChange in value?.documentChanges!!) {
                recipeList.add(dc.document.toObject(Recipe::class.java))
            }
            Log.i("DATA", recipeList.toString())
        }
    }

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
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        actionBarToggle.syncState()
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
        getRecipeData()
    }
}