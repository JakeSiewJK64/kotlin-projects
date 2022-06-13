package au.swin.joekanesiew_customapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val recipeName: TextView = view.findViewById(R.id.recipeName)
    private val recipeDescription: TextView = view.findViewById(R.id.recipeDescription)
    private val viewRecipeButton: Button = view.findViewById(R.id.viewRecipeButton)

    fun bind(recipe: Recipe, fragmentManager: FragmentManager) {
        recipeName.text = recipe.RecipeName
        recipeDescription.text = recipe.RecipeDescription
        viewRecipeButton.setOnClickListener {
            Snackbar.make(view, "${recipe.RecipeName} Clicked!", Snackbar.LENGTH_LONG)
                .setAction("OK") {}.show()
            val recipeFrag = NewRecipeFragment()
            val arg = Bundle()
            arg.putParcelable("RECIPE_DATA", recipe)
            recipeFrag.arguments = arg
            fragmentManager.beginTransaction().apply {
                replace(R.id.recipeFragmentFrame, recipeFrag)
                commit()
            }
        }
    }
}