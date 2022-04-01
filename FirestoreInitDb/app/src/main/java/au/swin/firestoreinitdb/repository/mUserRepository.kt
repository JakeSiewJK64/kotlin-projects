package au.swin.firestoreinitdb.repository

import android.content.Context
import au.swin.firestoreinitdb.models.mUserModel
import com.google.firebase.firestore.FirebaseFirestore

class mUserRepository() {
    fun getmUsersList(): ArrayList<mUserModel> {
        val db = FirebaseFirestore.getInstance()
        val userList = ArrayList<mUserModel>()

        db.collection("mUsers")
            .get()
            .addOnCompleteListener {
                for (user in it.result) {
                    userList.add(
                        mUserModel(
                            user.data["user_name"].toString(),
                            user.data["user_password"].toString(),
                            Integer.parseInt(user.data["user_role"].toString())
                        )
                    )
                }
            }
        return userList
    }
}