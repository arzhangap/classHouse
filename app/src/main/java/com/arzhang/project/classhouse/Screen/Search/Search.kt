package com.arzhang.project.classhouse.Screen.Search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.arzhang.project.classhouse.database.model.Course
import com.arzhang.project.classhouse.ui.components.CourseCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    category: String,
    onCourseClick: (Int) -> Unit
) {
    val courses by viewModel.uiState.collectAsState()
    val mockDATA = mutableListOf<Course>()
    repeat(8) {mockDATA.add(Course(1,"firsr","python",1,false))}
    Column {
        TopAppBar(title = { Text(category, textAlign = TextAlign.Center) })
    LazyColumn(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
            items(courses) { course ->
                CourseCard(course = course, onItemClick = {onCourseClick(course.id)}, modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(10.dp))
            }
    }
    }
}