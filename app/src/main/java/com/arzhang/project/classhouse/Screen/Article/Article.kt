package com.arzhang.project.classhouse.Screen.Article

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.arzhang.project.classhouse.Database.model.Article

@Composable
fun ArticleScreen(
    articleId: Int
) {
    val article = Article(articleId, "momo", "mom"," ok0o0d", 1)
    Column {
        Text(article.videoUrl)
        Text(article.name)
        Text(article.content)
    }
}