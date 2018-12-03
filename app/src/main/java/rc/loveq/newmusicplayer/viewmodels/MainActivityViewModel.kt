package rc.loveq.newmusicplayer.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import rc.loveq.newmusicplayer.MediaSessionConnection
import rc.loveq.newmusicplayer.utils.Event

/**
 * Author：Rc
 * 0n 2018/12/2 18:24
 */
class MainActivityViewModel(
    private val mediaSessionConnection: MediaSessionConnection
) : ViewModel() {

    val rootMediaId: LiveData<String> =
        Transformations.map(mediaSessionConnection.isConnected) { isConnected ->
            if (isConnected) {
                mediaSessionConnection.rootMediaId
            } else {
                null
            }
        }

    val navigateToMediaItem: LiveData<Event<String>> get() = _navigateToMediaItem
    private val _navigateToMediaItem = MutableLiveData<Event<String>>()


//    fun mediaItemClicked()

}