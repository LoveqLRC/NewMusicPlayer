package rc.loveq.newmusicplayer.media

import android.net.Uri
import android.support.v7.util.DiffUtil

/**
 * Author：Rc
 * 0n 2018/12/3 21:34
 */
data class MediaItemData(
    val mediaId: String,
    val title: String,
    val subtitle: String,
    val albumArtUri: Uri,
    val browsable: Boolean,
    var playbackRes: Int
) {
    companion object {
        const val PLAYBACK_RES_CHANGED = 1

        val diffCallback = object : DiffUtil.ItemCallback<MediaItemData>() {
            override fun areItemsTheSame(oldItem: MediaItemData, newItem: MediaItemData): Boolean {
                return oldItem.mediaId == newItem.mediaId
            }

            override fun areContentsTheSame(oldItem: MediaItemData, newItem: MediaItemData): Boolean {
                return oldItem.mediaId == newItem.mediaId && oldItem.playbackRes == newItem.playbackRes
            }

            override fun getChangePayload(oldItem: MediaItemData, newItem: MediaItemData): Any? {
                return if (oldItem.playbackRes != newItem.playbackRes) {
                    PLAYBACK_RES_CHANGED
                } else null
            }

        }
    }

}