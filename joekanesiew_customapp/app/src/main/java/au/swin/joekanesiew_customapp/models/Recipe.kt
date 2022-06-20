package au.swin.joekanesiew_customapp.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Recipe(
    var ObjectId: String? = "",
    var RecipeName: String? = "",
    var RecipeDescription: String? = "",
    var RecipeSteps: String? = "",
    var RecipeImages: String? = "",
    var Favorites: Boolean? = true,
    var PreparationTime: Int? = 0
): Parcelable
