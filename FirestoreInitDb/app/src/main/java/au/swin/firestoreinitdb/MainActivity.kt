package au.swin.firestoreinitdb

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import au.swin.firestoreinitdb.adapter.mUserAdapter
import au.swin.firestoreinitdb.models.mUserModel
import com.google.firebase.firestore.*
import com.google.firebase.firestore.EventListener
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userArrayList: ArrayList<mUserModel>
    private lateinit var db: FirebaseFirestore
    private lateinit var mAdapter: mUserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.mUser_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        userArrayList = arrayListOf()
        mAdapter = mUserAdapter(userArrayList)
        recyclerView.adapter = mAdapter

        EventChangeListenner()
    }

    private fun EventChangeListenner() {
        db = FirebaseFirestore.getInstance()
        db.collection("mUsers").addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    Log.e("FIREBASE_ERROR", error.message.toString())
                    return
                }
                for (dc: DocumentChange in value?.documentChanges!!) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        userArrayList.add(dc.document.toObject(mUserModel::class.java))
                    }
                }
                mAdapter.notifyDataSetChanged()
            }
        })
    }
}