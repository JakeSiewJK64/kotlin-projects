package au.swin.imagetutorial

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
}