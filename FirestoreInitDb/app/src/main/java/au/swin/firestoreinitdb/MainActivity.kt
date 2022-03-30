package au.swin.firestoreinitdb

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = FirebaseFirestore.getInstance()
        db.collection("mUsers")
            .get()
            .addOnCompleteListener {
                val users = it.result
                for(user in users) {
                    Log.d(TAG, "$user.id => ${user.data}")
                }
            }
    }
}