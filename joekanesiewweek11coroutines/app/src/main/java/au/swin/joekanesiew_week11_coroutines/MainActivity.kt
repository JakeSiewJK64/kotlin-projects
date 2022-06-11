package au.swin.joekanesiew_week11_coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tvInput = findViewById<TextView>(R.id.tv1)


        Log.i("ASD", "MAIN THREAD top: ${Thread.currentThread().name}")

        tvInput.text = doNetwork()

//        GlobalScope.launch(Dispatchers.IO) {
//            val networkOutput = doNetwork()
//            withContext(Dispatchers.Main) {
//                tvInput.text = networkOutput
//            }
//        }

        Log.i("ASD", "MAIN THREAD bottom: ${Thread.currentThread().name}")
    }

    private fun doNetwork(): String {
        Log.i("ASD", "THIS NORMAL FUNC DOING INTENSIVE WORK from ${Thread.currentThread().name}")
        Thread.sleep(10000)
        Log.i("ASD", "${Thread.currentThread().name} work completed!")
        return "Return value from network func."
    }
}