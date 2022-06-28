package au.swin.joekanesiew_customapp.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.swin.joekanesiew_customapp.R
import au.swin.joekanesiew_customapp.dao.RecipeDao
import au.swin.joekanesiew_customapp.adapters.RecipeAdapter
import au.swin.joekanesiew_customapp.models.Recipe
import com.google.firebase.firestore.FirebaseFirestore

class RecipeListFragment : Fragment(R.layout.fragment_recipe_list) {
    private lateinit var db: FirebaseFirestore
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeRecycler: RecyclerView
    private lateinit var recipeList: ArrayList<Recipe>
    private lateinit var recipeDao: RecipeDao

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        super.onCreate(savedInstanceState)
        recipeList = ArrayList()
        db = FirebaseFirestore.getInstance()
        recipeAdapter = RecipeAdapter(recipeList, parentFragmentManager)
        recipeRecycler = view.findViewById(R.id.recipeRecycler)
        recipeRecycler.apply {
            adapter = recipeAdapter
            layoutManager = LinearLayoutManager(view.context)
        }
        recipeDao = RecipeDao(db, view, parentFragmentManager)
        recipeDao.recipeChangeEventListener(recipeList, recipeAdapter)
    }
}