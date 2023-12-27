package com.arzhang.project.classhouse.ui.components

import android.net.Uri
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.zIndex
import androidx.lifecycle.LifecycleOwner
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView

@Composable
fun Exo(videoUri: String, modifier:Modifier = Modifier) {
        val context = LocalContext.current

        val exoPlayer = remember(context) {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(videoUri))
                    .build()
                setMediaItem(mediaItem)

                playWhenReady = false
            }
        }

    var isBuffer by remember { mutableStateOf(false) }
    exoPlayer.addListener(
        object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                isBuffer = playbackState == ExoPlayer.STATE_BUFFERING
            }

            override fun onIsLoadingChanged(isLoading: Boolean) {
                isBuffer = isLoading
            }
        }
    )

        Box(modifier = modifier) {
            if(isBuffer) {
                VideoProgressBar(Modifier.align(Alignment.Center))
            }
            val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
            DisposableEffect(lifecycleOwner) {
                onDispose {
                    exoPlayer.release()
                }
            }
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        layoutParams =
                            FrameLayout.LayoutParams(
                                ViewGroup.LayoutParams.MATCH_PARENT,
                                ViewGroup.LayoutParams.MATCH_PARENT
                            )
                    }
                }
            )
        }
}

@Composable
fun VideoProgressBar(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        modifier = modifier.width(64.dp).zIndex(1F),
        color = MaterialTheme.colorScheme.surfaceVariant,
    )
}
