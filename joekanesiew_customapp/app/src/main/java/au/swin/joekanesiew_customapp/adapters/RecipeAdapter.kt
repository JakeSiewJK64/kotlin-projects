package au.swin.joekanesiew_customapp.adapters

import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import au.swin.joekanesiew_customapp.GlobalConstants
import au.swin.joekanesiew_customapp.fragments.NewRecipeFragment
import au.swin.joekanesiew_customapp.R
import au.swin.joekanesiew_customapp.RecipeDao
import au.swin.joekanesiew_customapp.models.Recipe
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

class RecipeAdapter(
    private val recipeList: ArrayList<Recipe>,
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recipeviewmodel, parent, false) as View
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipeList[position])
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class RecipeViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val recipeName: TextView = view.findViewById(R.id.recipeName)
        private val recipeDescription: TextView = view.findViewById(R.id.recipeDescription)
        private val recipeImage: ImageView = view.findViewById(R.id.recipeImage)
        private val viewRecipeButton: Button = view.findViewById(R.id.viewRecipeButtonViewModel)
        private val deleteRecipeButton: Button = view.findViewById(R.id.deleteRecipeButtonViewModel)
        private val recipeDao = RecipeDao(FirebaseFirestore.getInstance(), view, fragmentManager)

        fun bind(recipe: Recipe) {
            recipeName.text = recipe.RecipeName
            recipeDescription.text = recipe.RecipeDescription

            // set imageview source to image obtained from firebase storage
            if (recipe.RecipeImages != null && recipe.RecipeImages!!.isNotEmpty()) {
                val storage = Firebase.storage(GlobalConstants.STORAGE_COLLECTION_URL)
                val storageRef = storage.reference
                storageRef.child("images/${recipe.RecipeImages}.jpg").getBytes(Long.MAX_VALUE)
                    .addOnSuccessListener {
                        recipeImage.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size))
                    }
            }

            deleteRecipeButton.setOnClickListener {
                recipeDao.deleteRecipe(recipe)
            }

            // if view button is tapped, switch to Recipe editor fragment
            viewRecipeButton.setOnClickListener {
                val recipeFrag = NewRecipeFragment()
                val arg = Bundle()
                arg.putParcelable("RECIPE_DATA", recipe)
                recipeFrag.arguments = arg
                fragmentManager.beginTransaction().apply {
                    replace(R.id.recipeFragmentFrame, recipeFrag)
                    commit()
                }
                Snackbar.make(
                    view,
                    String.format(
                        view.resources.getString(R.string.viewholder_clicked),
                        recipe.RecipeName
                    ),
                    Snackbar.LENGTH_LONG
                )
                    .setAction(view.resources.getString(R.string.okDialog)) {}.show()
            }
        }
    }
}