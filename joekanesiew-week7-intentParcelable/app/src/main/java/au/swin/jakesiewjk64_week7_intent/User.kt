package au.swin.jakesiewjk64_week7_intent

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val userPassword: String,
    val userEmail: String,
    val rememberUser: Boolean = false
): Parcelable
