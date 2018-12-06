package rc.loveq.newmusicplayer.media.notification

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import rc.loveq.newmusicplayer.R
import rc.loveq.newmusicplayer.media.MusicService
import rc.loveq.newmusicplayer.utils.ResourceHelper

class MediaNotificationManager(service: MusicService) : BroadcastReceiver() {
    private val REQUEST_CODE = 100

    private var mService = service
    private var mSessionToken: MediaSessionCompat.Token? = null
    private lateinit var mController: MediaControllerCompat

    private var mMetadata: MediaMetadataCompat? = null

    private var mPlaybackState: PlaybackStateCompat? = null

    private var mStarted = false

    private var mNotificationManager: NotificationManager

    private var mNotificationColor: Int = 0

    private lateinit var mPlayIntent: PendingIntent
    private lateinit var mPauseIntent: PendingIntent
    private lateinit var mPreviousIntent: PendingIntent
    private lateinit var mNextIntent: PendingIntent
    private lateinit var mStopIntent: PendingIntent


    init {
        updateSessionToken()

        mNotificationColor = ResourceHelper.getThemeColor(mService, R.attr.colorPrimary, Color.DKGRAY)

        mNotificationManager = mService.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val packageName = mService.packageName

        mPauseIntent = PendingIntent.getBroadcast(
            mService, REQUEST_CODE,
            Intent(ACTION_PAUSE).setPackage(packageName),
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        mPlayIntent = PendingIntent.getBroadcast(
            mService, REQUEST_CODE,
            Intent(ACTION_PLAY).setPackage(packageName),
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        mPreviousIntent = PendingIntent.getBroadcast(
            mService, REQUEST_CODE,
            Intent(ACTION_PREV).setPackage(packageName),
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        mNextIntent = PendingIntent.getBroadcast(
            mService, REQUEST_CODE,
            Intent(ACTION_NEXT).setPackage(packageName),
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        mStopIntent = PendingIntent.getBroadcast(
            mService, REQUEST_CODE,
            Intent(ACTION_STOP).setPackage(packageName),
            PendingIntent.FLAG_CANCEL_CURRENT
        )

        // Cancel all notifications to handle the case where the Service was killed and
        // restarted by the system.
        mNotificationManager.cancelAll()

    }

    fun startNotification() {
        if (!mStarted) {
            mMetadata = mController.metadata
            mPlaybackState = mController.playbackState


        }

    }


    private fun updateSessionToken() {
        val freshToken = mService.sessionToken
        if (mSessionToken == null && freshToken != null ||
            mSessionToken != null && mSessionToken!! != freshToken
        ) {
            if (mController != null) {
                mController.unregisterCallback()
            }

        }
    }


    override fun onReceive(context: Context?, intent: Intent?) {

    }


    private val mMediaControllerCallbacks = object : MediaControllerCompat.Callback() {

        override fun onPlaybackStateChanged(state: PlaybackStateCompat) {
            mPlaybackState = state
            if (state.state == PlaybackStateCompat.STATE_STOPPED ||
                state.state == PlaybackStateCompat.STATE_NONE
            ) {
                stopNotification()
            } else {
                createNotification()
            }
        }

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {

        }

        override fun onSessionDestroyed() {

        }

    }

    private fun createNotification(): Notification? {
        if (mMetadata == null || mPlaybackState == null) {
            return null
        }
        var description = mMetadata?.description

        val fetchArtUrl: String? = null

        val art: Bitmap? = null


    }

    private fun stopNotification() {
        if (mStarted) {
            mStarted = false
            mController.unregisterCallback(mMediaControllerCallbacks)

        }
    }


    companion object {
        const val ACTION_PAUSE = "rc.loveq.newmusicplayer.pause"
        const val ACTION_PLAY = "rc.loveq.newmusicplayer.play"
        const val ACTION_PREV = "rc.loveq.newmusicplayer.prev"
        const val ACTION_NEXT = "rc.loveq.newmusicplayer.next"
        const val ACTION_STOP = "rc.loveq.newmusicplayer.stop"
    }
}