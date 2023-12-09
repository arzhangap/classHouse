package com.arzhang.project.classhouse.Screen.Course

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.arzhang.project.classhouse.database.model.Article

@Composable
fun CourseScreen(
    viewModel: CourseViewModel,
    onItemClick: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val units = uiState.units
    val articles = uiState.articles
    val courseImage = uiState.course.image
    val context = LocalContext.current
    Surface(
        color = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(
            Modifier
                .animateContentSize()
        ) {
            if(courseImage.isNotEmpty()) {
                Image(
                    painter = painterResource(context.resources.getIdentifier(courseImage,"drawable",context.packageName)),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
                val icon = if (uiState.course.isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder
                Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                    Text(text = uiState.course.name, style = MaterialTheme.typography.headlineMedium, modifier = Modifier.padding(horizontal = 10.dp))
                    IconButton(onClick = { viewModel.updateFav() }) {
                        Icon(
                            icon,
                            contentDescription = null
                        )
                    }
                }
            }
            units.forEachIndexed { index, unit ->
                var isExpanded by remember { mutableStateOf(false) }
                Surface(
                    color = MaterialTheme.colorScheme.primary,
                    shadowElevation = 5.dp
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .clickable { isExpanded = !isExpanded }
                            .padding(10.dp)
                    ) {
                        Text(unit.unitNumber.toString())
                        Spacer(modifier = Modifier.padding(10.dp))
                        Text(unit.name)
                        Icon(
                            imageVector = Icons.Default.KeyboardArrowDown,
                            contentDescription = "expand"
                        )
                    }
                }
                if (isExpanded) {
                    CourseList(articles[index], onItemClick = { onItemClick(it) })
                }
            }
        }
    }
}

@Composable
fun CourseList(
    articles: List<Article>,
    onItemClick: (Int) -> Unit
) {
        Column(Modifier.padding(horizontal = 10.dp)) {
            articles.forEach {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onItemClick(it.id) }
                ) {
                    Text(it.name, modifier = Modifier.padding(10.dp))
                }
            }
        }
    }