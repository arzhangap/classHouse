package com.arzhang.project.classhouse.Screen.Article

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.arzhang.project.classhouse.ui.components.Exo

@Composable
fun ArticleScreen(
    viewModel: ArticleViewModel
) {
    val article by viewModel.uiState.collectAsState()
    // Declaring ExoPlayer
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = Modifier
            .padding(top = 18.dp, start = 5.dp, end = 5.dp)
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp))
    {
        Column {
        Exo(article.videoUrl, Modifier.height(200.dp))
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(10.dp), Arrangement.SpaceBetween) {
                Text(text = article.name, style = MaterialTheme.typography.titleSmall)
                Text(text = article.date, style = MaterialTheme.typography.titleSmall)
            }
            Spacer(modifier = Modifier.padding(top = 10.dp))
            Text(article.content, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.padding(horizontal = 10.dp))
        }
        }
    }