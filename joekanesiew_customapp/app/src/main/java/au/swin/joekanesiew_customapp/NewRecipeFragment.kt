package au.swin.joekanesiew_customapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore

class NewRecipeFragment : Fragment(R.layout.fragment_new_recipe) {

    private lateinit var db: FirebaseFirestore

    private lateinit var submitButton: Button
    private lateinit var recipeNameInput: TextInputLayout
    private lateinit var recipeDescInput: TextInputLayout
    private lateinit var recipeStepsInput: TextInputLayout
    private lateinit var title: TextView

    private fun upsertRecipe(recipe: Recipe, view: View) {
        db = FirebaseFirestore.getInstance()
        val dbCollection = db.collection("joekanesiew-recipe")
        val docId = dbCollection.document().id

        if (recipe.ObjectId != null) {
            dbCollection.document(recipe.ObjectId.toString()).set(recipe).addOnSuccessListener {
                Log.i("DATA", "[SUCCESS] ${recipe}")
                Snackbar.make(
                    view.findViewById(R.id.recipeFragmentLayout),
                    "Successfully updated new recipe!",
                    Snackbar.LENGTH_LONG
                ).setAction("OK") {}.show()
            }
        } else {
            dbCollection.document(docId).set(recipe).addOnSuccessListener {
                Snackbar.make(
                    view.findViewById(R.id.recipeFragmentLayout),
                    "Successfully added new recipe!",
                    Snackbar.LENGTH_LONG
                ).setAction("OK") {}.show()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton = view.findViewById(R.id.recipeSubmit)
        recipeNameInput = view.findViewById(R.id.recipeNameInput)
        recipeDescInput = view.findViewById(R.id.recipeDescInput)
        recipeStepsInput = view.findViewById(R.id.recipeStepsInput)
        title = view.findViewById(R.id.tvRecipeEditorTitle)

        val a = arguments?.getParcelable<Recipe>("RECIPE_DATA")

        recipeNameInput.editText?.setText(a?.RecipeName)
        recipeDescInput.editText?.setText(a?.RecipeDescription)
        recipeStepsInput.editText?.setText(a?.RecipeSteps)

        if (a != null) {
            Log.i("RECIPE_DATA", a.toString())

            title.text = resources.getString(R.string.editRecipe)
            submitButton.text = resources.getString(R.string.updateRecipe)
        }

        submitButton.setOnClickListener {

            var docId: String? = null
            if (a != null) docId = a.ObjectId.toString()

            upsertRecipe(
                Recipe(
                    docId,
                    recipeNameInput.editText?.text.toString(),
                    recipeDescInput.editText?.text.toString(),
                    recipeStepsInput.editText?.text.toString()
                ),
                view
            )
        }
    }
}