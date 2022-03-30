package au.swin.firestoreinitdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import au.swin.firestoreinitdb.models.mUserModel
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)
        val mFirestore = FirebaseFirestore.getInstance()
        mFirestore.firestoreSettings = FirebaseFirestoreSettings.Builder().build()
        mFirestore
            .collection("cafe-database")
            .document("mUser")
            .get()
            .addOnSuccessListener { document ->
                try {
                    if (document != null) {
                        var users = document.toObject(mUserModel::class.java) ?: mUserModel(
                            null,
                            null,
                            null
                        )
                        println(users)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            .addOnFailureListener { err ->
                println(err)
            }

    }
}