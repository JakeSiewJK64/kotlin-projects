package au.swin.joekanesiew_customapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.top_app_bar.*

class MainActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recipeList: ArrayList<Recipe>

    private lateinit var submitButton: Button
    private lateinit var recipeNameInput: TextInputLayout
    private lateinit var recipeDescInput: TextInputLayout
    private lateinit var recipeStepsInput: TextInputLayout

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

    private fun insertData(recipe: Recipe) {
        val dbCollection = db.collection("joekanesiew-recipe")
        val docId = dbCollection.document().id
        dbCollection.document(docId).set(recipe).addOnSuccessListener {
            Snackbar.make(
                findViewById(android.R.id.content),
                "Successfully added new recipe!",
                Snackbar.LENGTH_LONG
            ).setAction("OK") {}.show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        submitButton = findViewById(R.id.recipeSubmit)
        recipeNameInput = findViewById(R.id.recipeNameInput)
        recipeDescInput = findViewById(R.id.recipeDescInput)
        recipeStepsInput = findViewById(R.id.recipeStepsInput)

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
            if (it.itemId == R.id.newRecipe) {
                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Launching Add Recipe",
                    Snackbar.LENGTH_LONG
                )
                    .setAction("OK") {}.show()
            }
            true
        }

        findViewById<Button>(R.id.recipeListButton).setOnClickListener {
            val i = Intent(this, RecipeList::class.java)
            startActivity(i)
        }

        submitButton.setOnClickListener {
            insertData(
                Recipe(
                    recipeNameInput.editText?.text.toString(),
                    recipeDescInput.editText?.text.toString(),
                    recipeStepsInput.editText?.text.toString()
                )
            )
        }

        getRecipeData()
    }
}