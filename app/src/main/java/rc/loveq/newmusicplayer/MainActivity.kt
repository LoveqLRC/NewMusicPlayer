package rc.loveq.newmusicplayer

import android.arch.lifecycle.ViewModelProviders
import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import rc.loveq.newmusicplayer.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        volumeControlStream =AudioManager.STREAM_MUSIC

//        viewModel= ViewModelProviders.of(this,)
    }
}
