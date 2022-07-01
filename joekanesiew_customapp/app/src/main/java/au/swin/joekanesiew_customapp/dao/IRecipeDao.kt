package au.swin.joekanesiew_customapp.dao

import android.widget.ImageView
import au.swin.joekanesiew_customapp.adapters.RecipeAdapter
import au.swin.joekanesiew_customapp.models.Recipe

interface IRecipeDao {
    fun recipeChangeEventListener(recipeList: ArrayList<Recipe>, recipeAdapter: RecipeAdapter)
    fun upsertRecipe(recipe: Recipe,fileName: String, imageView: ImageView)
    fun deleteRecipe(recipe: Recipe)
}