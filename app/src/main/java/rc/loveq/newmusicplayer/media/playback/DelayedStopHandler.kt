package rc.loveq.newmusicplayer.media.playback

import android.os.Handler
import android.os.Message
import rc.loveq.newmusicplayer.media.MusicService
import java.lang.ref.WeakReference

class DelayedStopHandler(service: MusicService) : Handler() {
    val mWeakReference: WeakReference<MusicService> = WeakReference(service)

    override fun handleMessage(msg: Message?) {
        var musicService = mWeakReference.get()



    }
}