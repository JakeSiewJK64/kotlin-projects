package au.swin.joekanesiew_customapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class RecipeList : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeRecycler: RecyclerView
    private lateinit var recipeList: ArrayList<Recipe>

    private fun eventChangeListener() {
        db = FirebaseFirestore.getInstance()
        val collection = db.collection("joekanesiew-recipe")
        collection.addSnapshotListener { value, err ->
            if (err != null) {
                Log.e("ERROR", err.message.toString())
            }

            for (dc: DocumentChange in value?.documentChanges!!) {
                if (dc.type == DocumentChange.Type.ADDED) {
                    recipeList.add(dc.document.toObject(Recipe::class.java))
                }
            }
            recipeAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe_list)
        recipeList = ArrayList()
        recipeAdapter = RecipeAdapter(recipeList)
        recipeRecycler = findViewById(R.id.recipeRecycler)
        recipeRecycler.adapter = recipeAdapter
        recipeRecycler.layoutManager = LinearLayoutManager(this)
        eventChangeListener()
    }
}