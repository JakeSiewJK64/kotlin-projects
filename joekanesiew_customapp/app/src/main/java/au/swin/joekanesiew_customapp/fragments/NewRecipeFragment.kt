package au.swin.joekanesiew_customapp.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import au.swin.joekanesiew_customapp.GlobalConstants
import au.swin.joekanesiew_customapp.R
import au.swin.joekanesiew_customapp.dao.RecipeDao
import au.swin.joekanesiew_customapp.models.Recipe
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import java.io.InputStream

class NewRecipeFragment : Fragment(R.layout.fragment_new_recipe) {

    private lateinit var recipeDao: RecipeDao
    private var fileName = ""

    private lateinit var db: FirebaseFirestore
    private lateinit var storage: FirebaseStorage

    private lateinit var submitButton: Button
    private lateinit var deleteButton: Button
    private lateinit var recipeNameInput: TextInputLayout
    private lateinit var recipeDescInput: TextInputLayout
    private lateinit var recipeStepsInput: TextInputLayout
    private lateinit var recipeDurationInput: TextInputLayout
    private lateinit var recipeFavoriteCheckbox: CheckBox
    private lateinit var recipeImageUpload: ImageView
    private lateinit var title: TextView

    // method to validate input text against regex pattern
    private fun validateInput(input: String, regexPattern: String): Boolean {
        return input.matches(Regex(regexPattern))
    }

    // validates if the input is blank
    private fun validateBlank(input: TextInputLayout): Boolean {
        if (input.editText?.text?.length == 0) {
            input.error = resources.getString(R.string.recipe_stringEmpty)
            return false
        }
        return true
    }

    // displays basic snackbar with message
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
        recipeImageUpload = view.findViewById(R.id.recipeImageUpload)
        recipeDurationInput = view.findViewById(R.id.recipePrepTime)
        recipeFavoriteCheckbox = view.findViewById(R.id.recipeFavorite)
        db = FirebaseFirestore.getInstance()
        recipeDao = RecipeDao(db, view, parentFragmentManager)

        // launches separate image browser for user to upload image of recipe
        val resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val data: Intent? = result.data
                    if (data != null) {
                        val uri: Uri? = data.data
                        val inputStream: InputStream? =
                            view.context.contentResolver.openInputStream(uri!!)
                        val bMap: Bitmap = BitmapFactory.decodeStream(inputStream)
                        fileName = uri.path.toString()
                        recipeImageUpload.setImageBitmap(bMap)
                        Log.i("DATA", "[INTENT DATA]: $data")
                        Log.i("DATA", "[bMap]: $bMap")
                    }
                }
            }

        // if image view tapped, launch the above intent to browse for image
        recipeImageUpload.setOnClickListener {
            val i = Intent()
            i.type = "image/*"
            i.action = Intent.ACTION_GET_CONTENT
            resultLauncher.launch(i)
        }

        val a = arguments?.getParcelable<Recipe>("RECIPE_DATA")

        // checks if fragment argument is null = means new
        // if not null, means existing, populate the views with appropriate data
        if (a != null) {
            storage = Firebase.storage(GlobalConstants.STORAGE_COLLECTION_URL)
            val storageRef = storage.reference
            storageRef.child("images/${a.RecipeImages}.jpg").getBytes(Long.MAX_VALUE)
                .addOnSuccessListener {
                    recipeImageUpload.setImageBitmap(BitmapFactory.decodeByteArray(it, 0, it.size))
                }

            Log.i("RECIPE_DATA", a.toString())
            title.text = resources.getString(R.string.editRecipe)
            submitButton.text = resources.getString(R.string.updateRecipe)
            a.apply {
                recipeNameInput.editText?.setText(RecipeName)
                recipeDescInput.editText?.setText(RecipeDescription)
                recipeStepsInput.editText?.setText(RecipeSteps)
                recipeDurationInput.editText?.setText(PreparationTime.toString())
                recipeFavoriteCheckbox.isChecked = Favorites.toString().toBoolean()
            }
            deleteButton.isEnabled = true
            deleteButton.setTextColor(resources.getColor(R.color.red, null))
            deleteButton.visibility = View.VISIBLE
        }

        deleteButton.setOnClickListener {
            recipeDao.deleteRecipe(a!!)
        }

        submitButton.setOnClickListener {

            val validName = validateInput(recipeNameInput.editText?.text.toString(), "[A-Za-z ]+")

            val notEmptyName = validateBlank(recipeNameInput)
            val notEmptyDesc = validateBlank(recipeDescInput)
            val notEmptySteps = validateBlank(recipeStepsInput)
            val notEmptyDuration = validateBlank(recipeDurationInput)

            // checks if recipe name is valid.
            if (!validName) {
                recipeNameInput.editText?.error =
                    resources.getString(R.string.recipe_alphabets_only)
                displaySnackbar(resources.getString(R.string.recipe_alphabets_only), view)
            }

            // if recipe is new, generate an object id.
            // recipe's ObjectId attribute used to reference recipe object for DELETE.
            var docId: String? = null
            if (a != null) {
                docId = a.ObjectId.toString()
                fileName = a.RecipeImages.toString()
            }

            if (fileName.isEmpty()) {
                displaySnackbar("Please upload an image for the recipe!", view)
            }

            // validate all inputs, if true, upsert the recipe.
            if (fileName.isNotEmpty() && notEmptyName && notEmptyDesc && notEmptySteps && validName && notEmptyDuration) {
                recipeDao.upsertRecipe(
                    Recipe(
                        docId,
                        recipeNameInput.editText?.text.toString(),
                        recipeDescInput.editText?.text.toString(),
                        recipeStepsInput.editText?.text.toString(),
                        fileName,
                        recipeFavoriteCheckbox.isChecked,
                        recipeDurationInput.editText?.text.toString().toInt()
                    ), fileName, recipeImageUpload
                )
            }
        }
    }
}