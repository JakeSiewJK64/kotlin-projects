package au.swin.joekanesiew_core3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.InputStreamReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        resources.openRawResource(R.raw.)
        val myMedalistList = ArrayList<Medalist> ()
        myMedalistList.let {
            it.add(Medalist("a", 1))
            it.add(Medalist("b", 2))
            it.add(Medalist("c", 3))
        }
        val medalistAdapter = MedalistAdapter(myMedalistList)
        val mMedalistRecyclerView: RecyclerView = findViewById(R.id.mRecyclerView)
        mMedalistRecyclerView.adapter = medalistAdapter
        mMedalistRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}