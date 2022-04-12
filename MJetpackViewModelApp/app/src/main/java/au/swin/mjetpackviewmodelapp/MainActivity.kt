package au.swin.mjetpackviewmodelapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel

class ViewModelClass : ViewModel() {
    fun showRes() {
    }
}

class MainActivity : AppCompatActivity() {

    val mViewModel: ViewModelClass by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mViewModel.showRes()
    }
}