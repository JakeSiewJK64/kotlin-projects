package au.swin.joekanesiew_customapp

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {
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
                    recipeList.add(
                        Recipe(
                            dc.document.id,
                            dc.document["recipeName"].toString(),
                            dc.document["recipeDescription"].toString(),
                            dc.document["recipeSteps"].toString(),
                        )
                    )
                }
            }
            Log.i("DATA", recipeList.toString())
            recipeAdapter.notifyDataSetChanged()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        recipeList = ArrayList()
        recipeAdapter = RecipeAdapter(recipeList, parentFragmentManager)
        recipeRecycler = view.findViewById(R.id.recipeRecycler)
        recipeRecycler.adapter = recipeAdapter
        recipeRecycler.layoutManager = LinearLayoutManager(view.context)
        eventChangeListener()
    }
}