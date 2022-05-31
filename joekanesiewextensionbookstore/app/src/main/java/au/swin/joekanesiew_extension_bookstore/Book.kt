package au.swin.joekanesiew_extension_bookstore

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Book(
    val bookName: String,
    val bookRating: Float,
    val image: String
) : Parcelable
