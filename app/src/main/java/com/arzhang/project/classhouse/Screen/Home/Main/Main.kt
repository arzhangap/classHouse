package com.arzhang.project.classhouse.Screen.Home.Main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.arzhang.project.classhouse.Database.model.Course
import com.arzhang.project.classhouse.ui.components.CourseCard
import com.arzhang.project.classhouse.ui.theme.ClassHouseTheme

@Composable
fun MainScreen(
    onCourseClick: (Int) -> Unit
) {
    Column {
        Row {
            CourseCard(course = Course(1, "تست میکنم", image = "python", categoryId = 1, isFav = false), onItemClick = {
                onCourseClick(it)
            })
        }
    }
}

@Preview(showSystemUi = true, showBackground = true, device = "spec:width=411dp,height=891dp")
@Composable
fun MainScreenPreview() {
    ClassHouseTheme {
        MainScreen({})
    }
}