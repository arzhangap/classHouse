package com.arzhang.project.classhouse.Screen.Course

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.arzhang.project.classhouse.Database.model.Article
import com.arzhang.project.classhouse.Database.model.CourseUnit

@Composable
fun CourseScreen(
    units: List<CourseUnit>,
    onItemClick: (Int) -> Unit
) {
    Column() {
        units.forEach { unit ->
            Row(Modifier.fillMaxWidth()) {
                Text(unit.unitNumber.toString())
                Spacer(modifier = Modifier.padding(top = 10.dp))
                Text(unit.name)
                Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "expand")
            }
            CourseList(unit, onItemClick = {onItemClick(it)})
        }
    }
}

@Composable
fun CourseList(
    unit: CourseUnit,
    onItemClick: (Int) -> Unit
) {
//    val lessonList = someViewModel.getThat(courseId)
    val lessonList = listOf(Article(1,"${unit.name}","contentccccc","url", unit.id),
        Article(2,"${unit.name}","contentccccc","url", unit.id))
    Column(modifier = Modifier.background(Color.Gray, shape = RoundedCornerShape(10.dp))) {
      lessonList.forEach {
          Row(
              modifier = Modifier
                  .fillMaxWidth()
                  .clickable {onItemClick(it.id)}
          ) {
              Text("Hello, world!", modifier = Modifier.padding(10.dp))
          }
      }
    }

}