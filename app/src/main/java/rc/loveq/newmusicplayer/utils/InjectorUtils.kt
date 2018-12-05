package rc.loveq.newmusicplayer.utils

import android.content.ComponentName
import android.content.Context
import rc.loveq.newmusicplayer.MediaSessionConnection
import rc.loveq.newmusicplayer.media.MusicService
import rc.loveq.newmusicplayer.viewmodels.MainActivityViewModel

/**
 * Author：Rc
 * 0n 2018/12/3 22:13
 */
object InjectorUtils {

    private fun provideMediaSessionConnection(context: Context): MediaSessionConnection {
        return MediaSessionConnection.getInstance(context,
            ComponentName(context,MusicService::class.java))
    }

    fun provideMainActivityViewModel(context: Context): MainActivityViewModel.Factory {
        val applicationContext = context.applicationContext
        val mediaSessionConnection = provideMediaSessionConnection(applicationContext)
        return MainActivityViewModel.Factory(mediaSessionConnection)
    }

}