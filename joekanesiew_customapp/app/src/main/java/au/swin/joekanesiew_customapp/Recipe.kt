package au.swin.joekanesiew_customapp

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    var RecipeName: String? = "",
    var RecipeDescription: String? = "",
    var RecipeSteps: String? = ""
): Parcelable
