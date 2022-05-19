package au.swin.joekanesiew_week8_listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Custom Approach
        val myCustomAdapter = CustomAdapter()

        // Normal Approach
        val mListView: ListView = findViewById(R.id.mainListView)
        val codeArray = arrayOf(
            "Python",
            "Java",
            "Scala",
            "Kotlin",
            "SQL",
            "Angular",
            "React",
            "Visual Studio Code",
            "Android",
            "AI",
            "Machine Learning",
            "Arduino",
            "Raspberry PI",
            "Data Science",
        )
        val arrayAdapter: ArrayAdapter<String> =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, codeArray)

        mListView.adapter = myCustomAdapter

        mListView.onItemClickListener = AdapterView.OnItemClickListener{ _, _, position, _ ->
//            val itemView = mListView.getItemAtPosition(position) as String
//            Snackbar.make(mListView, "${itemView} clicked!", Snackbar.LENGTH_LONG).show()
        }
    }
}