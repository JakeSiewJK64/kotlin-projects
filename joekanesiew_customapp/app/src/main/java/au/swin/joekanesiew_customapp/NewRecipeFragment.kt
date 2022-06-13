package au.swin.joekanesiew_customapp

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
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

    private fun insertData(recipe: Recipe, view: View) {
        db = FirebaseFirestore.getInstance()
        val dbCollection = db.collection("joekanesiew-recipe")
        val docId = dbCollection.document().id
        dbCollection.document(docId).set(recipe).addOnSuccessListener {
            Snackbar.make(
                view.findViewById(R.id.recipeFragmentLayout),
                "Successfully added new recipe!",
                Snackbar.LENGTH_LONG
            ).setAction("OK") {}.show()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton = view.findViewById(R.id.recipeSubmit)
        recipeNameInput = view.findViewById(R.id.recipeNameInput)
        recipeDescInput = view.findViewById(R.id.recipeDescInput)
        recipeStepsInput = view.findViewById(R.id.recipeStepsInput)

        val a = arguments?.getParcelable<Recipe>("RECIPE_DATA")
        if (a != null) {
            Log.i("RECIPE_DATA", a.toString())
            recipeNameInput.editText?.setText(a.RecipeName)
            recipeDescInput.editText?.setText(a.RecipeDescription)
            recipeStepsInput.editText?.setText(a.RecipeSteps)
        }

        submitButton.setOnClickListener {
            insertData(
                Recipe(
                    recipeNameInput.editText?.text.toString(),
                    recipeDescInput.editText?.text.toString(),
                    recipeStepsInput.editText?.text.toString()
                ),
                view
            )
        }
    }
}