package com.arzhang.project.classhouse.Screen.Article

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.media3.common.util.Util
import androidx.media3.datasource.DefaultDataSourceFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.arzhang.project.classhouse.Database.model.Article
import com.arzhang.project.classhouse.ui.components.Exo

@Composable
fun ArticleScreen(
    articleId: Int
) {
    val article = Article(articleId, "momo", "mom","https://dl200.ftk.pw/?s=19&f=/user/shahab4/film/The.Love.Bug.1968.480p.Farsi.Dubbed.mp4", 1)
    // Declaring ExoPlayer
    Column {
        Exo(article.videoUrl, Modifier.height(200.dp))
        Text(article.name)
        Text(article.content)
    }
}