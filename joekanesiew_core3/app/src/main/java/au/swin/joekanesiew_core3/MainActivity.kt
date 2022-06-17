package au.swin.joekanesiew_core3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    // inflate menu when created based on menu.xml items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.medalistmenu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    // starts intent if the menu icon is pressed.
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val i = Intent(this, MedalistDetailsActivity::class.java)
        startActivity(i)
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mMedalistRecyclerView: RecyclerView = findViewById(R.id.mRecyclerView)
        val medallistList = ArrayList<MedalistModel>()
        val medalistAdapter = MedalistAdapter(supportFragmentManager, medallistList)

        /**
         * 1. Read each line from medallists.csv.
         * 2. split() method separates each value by comma into individual data.
         * 3. isDigitOnly() method returns true if the value only contains digits.
         */
        resources.openRawResource(R.raw.medallists)
            .bufferedReader()
            .lines()
            .skip(1)
            .forEach {
                val temp = it.split(",")
                medallistList.add(
                    MedalistModel(
                        name = temp[0],
                        IOC = temp[1],
                        gold = temp[3].toInt(),
                        silver = temp[4].toInt(),
                        bronze = temp[5].toInt(),
                    )
                )
            }

        /**
         * 1. sort medallist array list by total medals
         * 2. loop through medallist array list set isTop10 to true if index > 10
         */
        medallistList.apply {
            sortByDescending { i -> i.totalMedals }
            forEachIndexed { index, a ->
                if (index < 10) {
                    a.top10 = true
                }
            }
        }

        mMedalistRecyclerView.adapter = medalistAdapter
        mMedalistRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}