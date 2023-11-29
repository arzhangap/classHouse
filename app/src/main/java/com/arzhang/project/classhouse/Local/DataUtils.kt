package com.arzhang.project.classhouse.Local

import com.arzhang.project.classhouse.database.model.Category
import com.arzhang.project.classhouse.database.model.Course
import com.arzhang.project.classhouse.R

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
        Category(1,"ریاضی", R.drawable.python),
        Category(2,"برنامه نویسی",R.drawable.python),
        Category(3,"شیمی",R.drawable.python),
        Category(4,"نقاشی",R.drawable.python),
        Category(5,"عکاسی",R.drawable.python),
        Category(6,"فیلم",R.drawable.python),
        Category(7,"نقاشی",R.drawable.python),
        Category(8,"عکاسی",R.drawable.python),
        Category(9,"فیلم",R.drawable.python),
        Category(10,"فیلم",R.drawable.python),
        Category(11,"نقاشی",R.drawable.python),
        Category(12,"عکاسی",R.drawable.python),
    )
}