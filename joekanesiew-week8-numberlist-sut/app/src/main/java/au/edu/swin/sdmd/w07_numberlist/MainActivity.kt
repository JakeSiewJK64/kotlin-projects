package au.edu.swin.sdmd.w07_numberlist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menuindex, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val res = when (item.itemId) {
            R.id.longlist -> {
                displayList(100)
                true
            }
            R.id.shortlist -> {
                displayList(50)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
        return res
    }

    private fun displayList(length: Int) {
        val data = IntArray(length) { it }.filter { it % 3 == 0 || it % 5 == 0 }
        val numberList = findViewById<RecyclerView>(R.id.numberList)
        val myAdapter = NumberAdapter(data, this)
        numberList.adapter = myAdapter
        numberList.layoutManager = LinearLayoutManager(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        displayList(100)
    }
}