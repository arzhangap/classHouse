package com.arzhang.project.classhouse.Local

import com.arzhang.project.classhouse.Database.model.Category
import com.arzhang.project.classhouse.Database.model.Course
import java.sql.Date

fun genCourse(): List<Course> {
    return listOf(
        Course(
            id = 1,
            name = "آموزش پایتون",
            image = "R.drawable.python",
            categoryId = 1,
//            publishedDate = Date(20230711),
            isFav = false
        )
    )
}

object LocalDataProvider {
    val categories = listOf<Category>(
        Category(1,"ریاضی"),
        Category(2,"برنامه نویسی"),
        Category(3,"شیمی"),
        Category(4,"نقاشی"),
        Category(5,"عکاسی"),
        Category(6,"فیلم"),
    )
}