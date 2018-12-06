package rc.loveq.newmusicplayer.media

import android.app.PendingIntent
import android.content.Intent
import android.drm.DrmStore.Playback.STOP
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserServiceCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import rc.loveq.newmusicplayer.MEDIA_ID_ROOT
import rc.loveq.newmusicplayer.TAG
import rc.loveq.newmusicplayer.media.playback.DelayedStopHandler
import rc.loveq.newmusicplayer.media.playback.PlaybackManager

/**
 * Author：Rc
 * 0n 2018/12/2 11:50
 */
class MusicService : MediaBrowserServiceCompat(), PlaybackManager.PlaybackServiceCallback {

    private lateinit var mediaSession: MediaSessionCompat

    private var mDelayedStopHandler = DelayedStopHandler(this)
    // Delay stopSelf by using a handler.
    private var STOP_DELAY: Long = 3000

    override fun onPlaybackStart() {
        mediaSession.isActive = true

        mDelayedStopHandler.removeCallbacksAndMessages(null)

        // The service needs to continue running even after the bound client (usually a
        // MediaController) disconnects, otherwise the music playback will stop.
        // Calling startService(Intent) will keep the service running until it is explicitly killed.
        startService(Intent(applicationContext, MusicService::class.java))
    }


    override fun onPlaybackStop() {
        mediaSession.isActive = false
        // Reset the delayed stop handler, so after STOP_DELAY it will be executed again,
        // potentially stopping the service.
        mDelayedStopHandler.removeCallbacksAndMessages(null)
        mDelayedStopHandler.sendEmptyMessageDelayed(0, STOP_DELAY)
        stopForeground(true)

    }

    override fun onNotificationRequired() {

    }


    override fun onPlaybackStateUpdated(newState: PlaybackStateCompat) {

    }


    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "------onCreate------")

        val sessionIntent = packageManager?.getLaunchIntentForPackage(packageName)

        val sessionActivityPendingIntent = PendingIntent.getActivity(this, 0, sessionIntent, 0)


        mediaSession = MediaSessionCompat(this, "MusicService")
            .apply {
                setSessionActivity(sessionActivityPendingIntent)
                isActive = true
            }
        /**
         * In order for [MediaBrowserCompat.ConnectionCallback.onConnected] to be called,
         * a [MediaSessionCompat.Token] needs to be set on the [MediaBrowserServiceCompat].
         *
         * It is possible to wait to set the session token, if required for a specific use-case.
         * However, the token *must* be set by the time [MediaBrowserServiceCompat.onGetRoot]
         * returns, or the connection will fail silently. (The system will not even call
         * [MediaBrowserCompat.ConnectionCallback.onConnectionFailed].)
         */
        sessionToken = mediaSession.sessionToken


    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        Log.e(TAG, "------onGetRoot------")
        return BrowserRoot(MEDIA_ID_ROOT, null)
    }

    override fun onLoadChildren(
        parentMediaId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        Log.e(TAG, "------onLoadChildren------")


    }


}