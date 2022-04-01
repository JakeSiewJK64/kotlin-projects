package au.swin.firestoreinitdb

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import au.swin.firestoreinitdb.repository.mUserRepository

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mUserRepository().getmUsersList()   
    }
}