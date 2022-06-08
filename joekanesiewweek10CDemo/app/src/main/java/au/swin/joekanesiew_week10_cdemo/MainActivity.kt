package au.swin.joekanesiew_week10_cdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.text.isDigitsOnly
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val list = mutableListOf<MyWord>()
        val mRecyclerView = findViewById<RecyclerView>(R.id.mRecyclerView)
        resources.openRawResource(R.raw.medal).bufferedReader().forEachLine {
            val temp = it.split(",")
            if (temp[3].isDigitsOnly()) {
                list.add(MyWord(temp[0], temp[3].toInt()))
            }
        }
        val mAdapter = MyAdapter(list)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mAdapter
    }
}