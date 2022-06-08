package au.swin.joekanesiew_core3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.medalistmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i = Intent(this, MedalistHistory::class.java)
        startActivity(i)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myMedalistList = ArrayList<Medalist>()
        val medalistAdapter = MedalistAdapter(myMedalistList)
        val mMedalistRecyclerView: RecyclerView = findViewById(R.id.mRecyclerView)
        resources.openRawResource(R.raw.medallists).bufferedReader().forEachLine {
            val temp = it.split(",")
            if (temp[3].isDigitsOnly()) {
                myMedalistList.add(Medalist(temp[0], temp[3].toInt()))
            }
        }
        mMedalistRecyclerView.adapter = medalistAdapter
        mMedalistRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}