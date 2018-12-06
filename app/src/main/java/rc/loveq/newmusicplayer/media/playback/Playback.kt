package rc.loveq.newmusicplayer.media.playback

interface Playback {

    fun start()

    fun stop(notifyListeners: Boolean)

    fun setState(state: Int)

    fun getState(): Int


    fun isConnected(): Boolean

    fun isPlaying(): Boolean

    fun getCurrentStreamPosition(): Long

    fun updateLastKnownStreamPosition()

    fun play()

    fun pause()

    fun seekTo(position: Long)

    fun setCurrentMediaId(mediaId: String)

    fun getCurrentMediaId(): String


    interface Callback {
        fun onCompletion()

        fun onPlaybackStatusChanged(state: Int)

        fun onError(error: String)

        fun setCurrentMediaId(mediaId: String)
    }


    fun setCallback(callback: Callback)

}