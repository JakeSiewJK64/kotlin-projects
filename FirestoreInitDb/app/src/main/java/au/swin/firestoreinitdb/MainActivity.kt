package au.swin.firestoreinitdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import au.swin.firestoreinitdb.models.mUserModel
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()
        val userList = arrayListOf<mUserModel>()

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
                println(userList)
            }
    }
}