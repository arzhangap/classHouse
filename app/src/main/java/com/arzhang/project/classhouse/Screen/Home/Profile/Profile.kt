package com.arzhang.project.classhouse.Screen.Home.Profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.arzhang.project.classhouse.ui.components.CourseCard

@Composable
fun ProfileScreen(
    viewModel: ProfileViewModel = hiltViewModel(),
    onClick: (Int) -> Unit
) {
//    val mockDATA = mutableListOf<Course>()
//    repeat(8) {mockDATA.add(Course(1,"firsr","python",1,false))}
    val courses by viewModel.uiState.collectAsState()
    Column(verticalArrangement = Arrangement.SpaceBetween) {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                "موردعلاقه های شما",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(10.dp)
            )
            Icon(imageVector = Icons.Filled.ThumbUp, contentDescription = null)
        }
        Divider(thickness = 1.dp, color = Color.Gray)
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
           items(courses) { course ->
               CourseCard(course = course, onItemClick = {onClick(course.id)},Modifier.padding(8.dp))
           }
        }
    }
}