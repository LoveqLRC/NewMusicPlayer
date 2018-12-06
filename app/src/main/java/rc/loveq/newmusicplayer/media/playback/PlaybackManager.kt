package rc.loveq.newmusicplayer.media.playback

import android.support.v4.media.session.PlaybackStateCompat


class PlaybackManager : Playback.Callback {

    override fun onCompletion() {

    }

    override fun onPlaybackStatusChanged(state: Int) {

    }

    override fun onError(error: String) {

    }

    override fun setCurrentMediaId(mediaId: String) {

    }


    interface PlaybackServiceCallback {
        fun onPlaybackStart()

        fun onNotificationRequired()

        fun onPlaybackStop()

        fun onPlaybackStateUpdated(newState: PlaybackStateCompat)
    }

}