package au.swin.joekanesiew_customapp.dao

import android.widget.ImageView
import au.swin.joekanesiew_customapp.adapters.RecipeAdapter
import au.swin.joekanesiew_customapp.models.Recipe
import com.google.firebase.storage.FirebaseStorage

interface IRecipeDao {
    fun recipeChangeEventListener(recipeList: ArrayList<Recipe>, recipeAdapter: RecipeAdapter)
    fun upsertRecipe(recipe: Recipe, firebaseStorage: FirebaseStorage,fileName: String, imageView: ImageView)
    fun deleteRecipe(recipe: Recipe)
}