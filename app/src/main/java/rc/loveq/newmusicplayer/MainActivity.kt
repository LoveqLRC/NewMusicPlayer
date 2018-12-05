package rc.loveq.newmusicplayer

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.media.AudioManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import rc.loveq.newmusicplayer.utils.InjectorUtils
import rc.loveq.newmusicplayer.viewmodels.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        volumeControlStream = AudioManager.STREAM_MUSIC

        viewModel = ViewModelProviders.of(this, InjectorUtils.provideMainActivityViewModel(this))
            .get(MainActivityViewModel::class.java)


        viewModel.rootMediaId.observe(this, Observer<String> { rootMediaId ->
            Log.e(TAG, "------rootMediaId : $rootMediaId------")
        })
    }
}
