package rc.loveq.newmusicplayer.viewmodels

import android.arch.lifecycle.*
import rc.loveq.newmusicplayer.MediaSessionConnection
import rc.loveq.newmusicplayer.media.MediaItemData
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


    fun mediaItemClicked(clickedItem: MediaItemData) {
        if (clickedItem.browsable) {
            browseToItem(clickedItem)
        } else {
            playMedia(clickedItem)
        }
    }


    private fun browseToItem(clickedItem: MediaItemData) {

    }

    private fun playMedia(clickedItem: MediaItemData) {

    }


    class Factory(private val mediaSessionConnection: MediaSessionConnection) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainActivityViewModel(mediaSessionConnection) as T
        }
    }

}