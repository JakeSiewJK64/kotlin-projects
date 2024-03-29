package au.swin.core1_siewjoekane_c1

import android.graphics.Color
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import au.swin.core1_siewjoekane_c1.viewModels.NumberViewModel

class MainActivity : AppCompatActivity() {
    private val mViewModel: NumberViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mScoreButton: Button = findViewById(R.id.mButton1)
        val mStealButton: Button = findViewById(R.id.mButton2)
        val mResetButton: Button = findViewById(R.id.mButton3)
        val mTextView: TextView = findViewById(R.id.mTextView)
        val mMediaPlayer: MediaPlayer = MediaPlayer.create(this, R.raw.crowd)

        mTextView.text = mViewModel.number.toString()
        mTextView.setTextColor(Color.parseColor(mViewModel.colorCode))

        Log.i("MY_INFO", "color: ${mViewModel.colorCode}, number: ${mViewModel.number}")

        // TODO: Implement button to increment counter by 1.
        mScoreButton.setOnClickListener {
            mViewModel.playSound(mMediaPlayer)
            if (mViewModel.number < 15) {
                mViewModel.number += 1
                mTextView.text = mViewModel.number.toString()
                mViewModel.showColorCode()
                mTextView.setTextColor(Color.parseColor(mViewModel.colorCode))
            }
            Log.i("MY_INFO", "color: ${mViewModel.colorCode}, number: ${mViewModel.number}")
        }

        // TODO: Implement button decrement number by 1.
        mStealButton.setOnClickListener {
            if (mViewModel.number > 0) {
                mViewModel.number -= 1
                mTextView.text = mViewModel.number.toString()
                mViewModel.showColorCode()
                mTextView.setTextColor(Color.parseColor(mViewModel.colorCode))
            }
            Log.i("MY_INFO", "color: ${mViewModel.colorCode}, number: ${mViewModel.number}")
        }

        // TODO: Implement button to set number to 0
        mResetButton.setOnClickListener {
            mViewModel.number = 0
            mViewModel.showColorCode()
            mTextView.setTextColor(Color.parseColor(mViewModel.colorCode))
            mTextView.text = mViewModel.number.toString()
            Log.i("MY_INFO", "color: ${mViewModel.colorCode}, number: ${mViewModel.number}")
        }
    }
}