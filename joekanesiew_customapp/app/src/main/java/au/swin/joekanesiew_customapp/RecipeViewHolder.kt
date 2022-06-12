package au.swin.joekanesiew_customapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val recipeName: TextView = view.findViewById(R.id.recipeName)
    private val recipeDescription: TextView = view.findViewById(R.id.recipeDescription)

    fun bind(recipe: Recipe) {
        recipeName.text = recipe.RecipeName
        recipeDescription.text = recipe.RecipeDescription
    }
}