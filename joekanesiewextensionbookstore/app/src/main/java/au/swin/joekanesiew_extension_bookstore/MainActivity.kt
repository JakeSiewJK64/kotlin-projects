package au.swin.joekanesiew_extension_bookstore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mRecyclerView: RecyclerView = findViewById(R.id.mRecyclerView)
        val bookList = ArrayList<Book>()
        val mAdapter = BookshopAdapter(bookList)

        bookList.let {
            it.add(Book("Spy x Family", 5f, R.drawable.spyfam.toString()))
            it.add(Book("Kaguya Sama", 2f, R.drawable.kaguya.toString()))
            it.add(Book("Tokyo Revengers", 4f, R.drawable.tokyorevengers.toString()))
        }

        mRecyclerView.adapter = mAdapter
        mRecyclerView.layoutManager = LinearLayoutManager(this)
    }
}