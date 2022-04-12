package au.swin.imagetutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView

class MainActivity : AppCompatActivity() {


    override fun onStop() {
        super.onStop()
        Log.i("LIFECYCLE", "stopped")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            Log.i(
                "LIFECYCLE",
                "M_REVENUE: ${savedInstanceState.getInt("M_REVENUE", 0)}"
            )
        } else {
            Log.i("LIFECYCLE", "saved instance is null")
        }

        val mImageView: ImageView = findViewById(R.id.mImageView)
        var isShowOther = false

        mImageView.setOnClickListener {
            isShowOther = !isShowOther
            if (!isShowOther) {
                mImageView.setImageResource(R.drawable.warning)
            } else {
                mImageView.setImageResource(R.drawable.check)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.i("LIFECYCLE", "onSaveInstanceState Called")
        outState.putInt("M_REVENUE", 1)
        Log.i("LIFECYCLE", "mstate: ${outState.getInt("M_REVENUE", 0)}")
    }
}
