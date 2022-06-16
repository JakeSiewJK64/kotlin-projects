package au.swin.joekanesiew_customapp

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private val recipeName: TextView = view.findViewById(R.id.recipeName)
    private val recipeDescription: TextView = view.findViewById(R.id.recipeDescription)
    private val recipeImage: ImageView = view.findViewById(R.id.recipeImage)
    private val viewRecipeButton: Button = view.findViewById(R.id.viewRecipeButton)

    fun bind(recipe: Recipe, fragmentManager: FragmentManager) {
        recipeName.text = recipe.RecipeName
        recipeDescription.text = recipe.RecipeDescription

        // todo: set image queried from firebase storage
        if (recipe.RecipeImages != null && recipe.RecipeImages!!.isNotEmpty()) {
            val storage = Firebase.storage("gs://jakesiewjk64-customapp.appspot.com")
            val storageRef = storage.reference
            storageRef.child("images/${recipe.RecipeImages}.jpg").getBytes(Long.MAX_VALUE)
                .addOnSuccessListener {
                    recipeImage.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size))
                }

        }

        viewRecipeButton.setOnClickListener {
            Snackbar.make(
                view,
                String.format(
                    view.resources.getString(R.string.viewholder_clicked),
                    recipe.RecipeName
                ),
                Snackbar.LENGTH_LONG
            )
                .setAction(view.resources.getString(R.string.okDialog)) {}.show()
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