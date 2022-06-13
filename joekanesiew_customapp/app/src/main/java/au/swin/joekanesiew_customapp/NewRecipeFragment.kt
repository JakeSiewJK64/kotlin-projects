package au.swin.joekanesiew_customapp

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*

class NewRecipeFragment : Fragment(R.layout.fragment_new_recipe) {

    private val DATEFORMAT = "yyyy-mm-dd HH:MM:SS"

    private lateinit var db: FirebaseFirestore

    private lateinit var submitButton: Button
    private lateinit var deleteButton: Button
    private lateinit var recipeNameInput: TextInputLayout
    private lateinit var recipeDescInput: TextInputLayout
    private lateinit var recipeStepsInput: TextInputLayout
    private lateinit var title: TextView

    private fun upsertRecipe(recipe: Recipe, view: View) {
        db = FirebaseFirestore.getInstance()
        val dbCollection = db.collection("joekanesiew-recipe")
        val eventLogCollection = db.collection("joekanesiew-eventlog")

        if (recipe.ObjectId != null) {
            dbCollection.document(recipe.ObjectId.toString()).set(recipe).addOnSuccessListener {
                Log.i("DATA", "[SUCCESS] ${recipe}")
                Snackbar.make(
                    view.findViewById(R.id.recipeFragmentLayout),
                    String.format(
                        resources.getString(R.string.recipeUpdateSuccess),
                        recipe.RecipeName
                    ),
                    Snackbar.LENGTH_LONG
                ).setAction(resources.getString(R.string.okDialog)) {}.show()
                eventLogCollection.document().set(
                    EventLog(
                        null,
                        String.format(
                            resources.getString(R.string.eventlog_update),
                            recipe.RecipeName
                        ),
                        SimpleDateFormat(
                            DATEFORMAT,
                            Locale.getDefault()
                        ).format(Calendar.getInstance().time),
                        EventLogEnum.UPDATE
                    )
                )
            }
        } else {
            val docId = dbCollection.document().id
            dbCollection.document(docId).set(recipe).addOnSuccessListener {
                Snackbar.make(
                    view.findViewById(R.id.recipeFragmentLayout),
                    resources.getString(R.string.recipeDeleteSuccess),
                    Snackbar.LENGTH_LONG
                ).setAction(resources.getString(R.string.okDialog)) {}.show()
                eventLogCollection.document().set(
                    EventLog(
                        null,
                        String.format(
                            resources.getString(R.string.eventlog_create),
                            recipe.RecipeName
                        ),
                        SimpleDateFormat(
                            DATEFORMAT,
                            Locale.getDefault()
                        ).format(Calendar.getInstance().time),
                        EventLogEnum.CREATE
                    )
                )
            }
        }
    }

    private fun deleteRecipe(recipe: Recipe, view: View) {
        val builder = AlertDialog.Builder(view.context)
        val deletedRecipeBuilder = AlertDialog.Builder(view.context)

        deletedRecipeBuilder.setTitle(resources.getString(R.string.recipeDeleted))
        deletedRecipeBuilder.setMessage(
            String.format(
                resources.getString(R.string.returningRecipeList),
                recipe.RecipeName
            )
        )
        deletedRecipeBuilder.setPositiveButton(resources.getString(R.string.okDialog)) { _, _ ->
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.recipeFragmentFrame, RecipeListFragment())
                commit()
            }
        }

        builder.setTitle(resources.getString(R.string.deleteRecipeConfirmationTitle))
        builder.setMessage(
            String.format(
                resources.getString(R.string.deleteRecipeConfirmationMessage),
                recipe.RecipeName
            )
        )
        builder.setNegativeButton("Cancel") { _, _ ->
        }

        builder.setPositiveButton(resources.getString(R.string.okDialog)) { _, _ ->
            db = FirebaseFirestore.getInstance()
            val recipeCollection = db.collection("joekanesiew-recipe")
            val eventLogCollection = db.collection("joekanesiew-eventlog")
            recipeCollection.document(recipe.ObjectId.toString()).delete()
                .addOnSuccessListener {
                    Snackbar.make(
                        view.findViewById(R.id.recipeFragmentLayout),
                        resources.getString(R.string.recipeDeletedSnackbar),
                        Snackbar.LENGTH_LONG
                    ).setAction("OK") {}.show()
                    deletedRecipeBuilder.show()
                    eventLogCollection.document().set(
                        EventLog(
                            null,
                            String.format(
                                resources.getString(R.string.eventlog_delete),
                                recipe.RecipeName
                            ),
                            SimpleDateFormat(
                                DATEFORMAT,
                                Locale.getDefault()
                            ).format(Calendar.getInstance().time),
                            EventLogEnum.DELETE
                        )
                    )
                }
        }
        builder.show()
    }

    private fun validateInput(input: String, regexPattern: String): Boolean {
        return input.matches(Regex(regexPattern))
    }

    private fun validateBlank(input: TextInputLayout): Boolean {
        if (input.editText?.text?.length == 0) {
            input.error = resources.getString(R.string.recipe_stringEmpty)
            return false
        }
        return true
    }

    private fun displaySnackbar(message: String, view: View) {
        Snackbar.make(
            view,
            message,
            Snackbar.LENGTH_LONG
        ).setAction("OK") {}.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        submitButton = view.findViewById(R.id.recipeSubmit)
        deleteButton = view.findViewById(R.id.recipeDeleteButton)
        recipeNameInput = view.findViewById(R.id.recipeNameInput)
        recipeDescInput = view.findViewById(R.id.recipeDescInput)
        recipeStepsInput = view.findViewById(R.id.recipeStepsInput)
        title = view.findViewById(R.id.tvRecipeEditorTitle)

        val a = arguments?.getParcelable<Recipe>("RECIPE_DATA")

        if (a != null) {
            Log.i("RECIPE_DATA", a.toString())
            title.text = resources.getString(R.string.editRecipe)
            submitButton.text = resources.getString(R.string.updateRecipe)
            a.let {
                recipeNameInput.editText?.setText(it.RecipeName)
                recipeDescInput.editText?.setText(it.RecipeDescription)
                recipeStepsInput.editText?.setText(it.RecipeSteps)
            }
            deleteButton.isEnabled = true
            deleteButton.setTextColor(resources.getColor(R.color.red, null))
            deleteButton.visibility = View.VISIBLE
        }

        deleteButton.setOnClickListener {
            deleteRecipe(a!!, view)
        }

        submitButton.setOnClickListener {

            val validName = validateInput(recipeNameInput.editText?.text.toString(), "[A-Za-z ]+")

            val notEmptyName = validateBlank(recipeNameInput)
            val notEmptyDesc = validateBlank(recipeDescInput)
            val notEmptySteps = validateBlank(recipeStepsInput)

            if (!validName) {
                recipeNameInput.editText?.error =
                    resources.getString(R.string.recipe_alphabets_only)
                displaySnackbar(resources.getString(R.string.recipe_alphabets_only), view)
            }

            var docId: String? = null
            if (a != null) docId = a.ObjectId.toString()

            if (notEmptyName && notEmptyDesc && notEmptySteps && validName) {
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
}