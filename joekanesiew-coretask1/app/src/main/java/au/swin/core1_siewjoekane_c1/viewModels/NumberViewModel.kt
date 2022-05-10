package au.swin.core1_siewjoekane_c1.viewModels

import androidx.lifecycle.ViewModel
import android.media.MediaPlayer
import android.util.Log

class NumberViewModel : ViewModel() {
    var number = 0
    var colorCode = "#000000"

    // TODO: Method to play MediaPlayer sound.
    fun playSound(mMediaPlayer: MediaPlayer) {
        if(number == 15) {
            mMediaPlayer.start()
        }
    }

    // TODO: Method to returns color string
    fun showColorCode(): String {
        colorCode = when(number) {
            in 5..9 -> "#0000FF"
            in 10..15 -> "#00FF00"
            else -> "#000000"
        }
        Log.i("M_COLOR_SELECTION", "Color returned ViewModel: $colorCode")
        return colorCode
    }
}