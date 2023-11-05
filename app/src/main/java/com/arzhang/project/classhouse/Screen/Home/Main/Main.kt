package com.arzhang.project.classhouse.Screen.Home.Main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.arzhang.project.classhouse.Database.model.Course
import com.arzhang.project.classhouse.ui.components.CourseCard

@Composable
fun MainScreen(
    navController: NavController
) {
    Column {
        Row {
            CourseCard(course = Course(1, "تست میکنم", image = "python", categoryId = 1, isFav = false), onItemClick = {
                navController.navigate("article/1")
            })
        }
    }
}