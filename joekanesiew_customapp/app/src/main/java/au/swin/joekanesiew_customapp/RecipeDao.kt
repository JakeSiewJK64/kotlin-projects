package au.swin.joekanesiew_customapp

import android.app.AlertDialog
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.ScrollView
import androidx.fragment.app.FragmentManager
import au.swin.joekanesiew_customapp.fragments.RecipeListFragment
import au.swin.joekanesiew_customapp.models.EventLog
import au.swin.joekanesiew_customapp.models.Recipe
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.io.ByteArrayOutputStream
import java.text.SimpleDateFormat
import java.util.*

class RecipeDao(
    private val firestoreInstance: FirebaseFirestore,
    private val view: View,
    private val fragmentManager: FragmentManager
) {
    // upsert recipe = insert if no object id, update if object id present
    fun upsertRecipe(
        recipe: Recipe,
        firebaseStorage: FirebaseStorage,
        filename: String,
        recipeImageUpload: ImageView
    ) {
        val storageRef = firebaseStorage.reference
        val recipeImageRef = storageRef.child("images/$filename.jpg")

        val dbCollection = firestoreInstance.collection(GlobalConstants.RECIPE_COLLECTION_PATH)
        val eventLogCollection =
            firestoreInstance.collection(GlobalConstants.EVENTLOG_COLLECTION_PATH)

        /**
         * 1. retrieving drawable from imageview
         * 2. convert bitmap to byte array
         * 3. upload image to Firebase Storage
         */
        val bitmap = (recipeImageUpload.drawable as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val uploadTask = recipeImageRef.putBytes(data)
        uploadTask.addOnSuccessListener { taskSnapshot ->
            Snackbar.make(view, "Done Upload!$taskSnapshot", Snackbar.LENGTH_LONG)
                .setAction("OK") {}.show()
        }

        // if existing recipe -> update
        // if recipe does not have object id, means recipe is new, update
        if (recipe.ObjectId != null) {
            dbCollection.document(recipe.ObjectId.toString()).set(recipe).addOnSuccessListener {
                Log.i("DATA", "[SUCCESS] $recipe")
                Snackbar.make(
                    view.findViewById(R.id.recipeFragmentLayout),
                    String.format(
                        view.resources.getString(R.string.recipeUpdateSuccess),
                        recipe.RecipeName
                    ),
                    Snackbar.LENGTH_LONG
                ).setAction(view.resources.getString(R.string.okDialog)) {
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.recipeFragmentFrame, RecipeListFragment())
                        commit()
                        view.findViewById<ScrollView>(R.id.recipeFragmentLayout).removeAllViews()
                    }
                }.show()
                // add update recipe event to event log
                eventLogCollection.document().set(
                    EventLog(
                        null,
                        String.format(
                            view.resources.getString(R.string.eventlog_update),
                            recipe.RecipeName
                        ),
                        SimpleDateFormat(
                            GlobalConstants.GLOBAL_DATE_FORMAT,
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
                    view.resources.getString(R.string.recipeDeleteSuccess),
                    Snackbar.LENGTH_LONG
                ).setAction(view.resources.getString(R.string.okDialog)) {
                    fragmentManager.beginTransaction().apply {
                        replace(R.id.recipeFragmentFrame, RecipeListFragment())
                        commit()
                        view.findViewById<ScrollView>(R.id.recipeFragmentLayout).removeAllViews()
                    }
                }.show()
                eventLogCollection.document().set(
                    EventLog(
                        null,
                        String.format(
                            view.resources.getString(R.string.eventlog_create),
                            recipe.RecipeName
                        ),
                        SimpleDateFormat(
                            GlobalConstants.GLOBAL_DATE_FORMAT,
                            Locale.getDefault()
                        ).format(Calendar.getInstance().time),
                        EventLogEnum.CREATE
                    )
                )
            }
        }
    }

    // responsible for deleting a recipe
    fun deleteRecipe(recipe: Recipe) {
        val builder = AlertDialog.Builder(view.context)
        val deletedRecipeBuilder = AlertDialog.Builder(view.context)

        deletedRecipeBuilder.setTitle(view.resources.getString(R.string.recipeDeleted))
        deletedRecipeBuilder.setMessage(
            String.format(
                view.resources.getString(R.string.returningRecipeList),
                recipe.RecipeName
            )
        )
        deletedRecipeBuilder.setPositiveButton(view.resources.getString(R.string.okDialog)) { _, _ ->
            fragmentManager.beginTransaction().apply {
                replace(R.id.recipeFragmentFrame, RecipeListFragment())
                commit()
            }
        }

        builder.setTitle(view.resources.getString(R.string.deleteRecipeConfirmationTitle))
        builder.setMessage(
            String.format(
                view.resources.getString(R.string.deleteRecipeConfirmationMessage),
                recipe.RecipeName
            )
        )

        builder.setNegativeButton("Cancel") { _, _ ->
        }

        builder.setPositiveButton(view.resources.getString(R.string.okDialog)) { _, _ ->
            val recipeCollection =
                firestoreInstance.collection(GlobalConstants.RECIPE_COLLECTION_PATH)
            val eventLogCollection =
                firestoreInstance.collection(GlobalConstants.EVENTLOG_COLLECTION_PATH)
            recipeCollection.document(recipe.ObjectId.toString()).delete()
                .addOnSuccessListener {
                    Snackbar.make(
                        view,
                        view.resources.getString(R.string.recipeDeletedSnackbar),
                        Snackbar.LENGTH_LONG
                    ).setAction("OK") {}.show()
                    deletedRecipeBuilder.show()
                    eventLogCollection.document().set(
                        EventLog(
                            null,
                            String.format(
                                view.resources.getString(R.string.eventlog_delete),
                                recipe.RecipeName
                            ),
                            SimpleDateFormat(
                                GlobalConstants.GLOBAL_DATE_FORMAT,
                                Locale.getDefault()
                            ).format(Calendar.getInstance().time),
                            EventLogEnum.DELETE
                        )
                    )
                }
        }
        builder.show()
    }
}