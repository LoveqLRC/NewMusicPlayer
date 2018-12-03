package rc.loveq.newmusicplayer.media

import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaBrowserServiceCompat
import rc.loveq.newmusicplayer.MEDIA_ID_ROOT

/**
 * Author：Rc
 * 0n 2018/12/2 11:50
 */
class MusicService : MediaBrowserServiceCompat() {

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        return BrowserRoot(MEDIA_ID_ROOT, null)
    }

    override fun onLoadChildren(
        parentMediaId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {


    }


}