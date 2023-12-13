package com.arzhang.project.classhouse.Local

import com.arzhang.project.classhouse.R
import com.arzhang.project.classhouse.database.model.Category
import com.arzhang.project.classhouse.database.model.Course

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
        Category(1,"ریاضی", R.drawable.math_category),
        Category(2,"برنامه نویسی",R.drawable.programming_category),
        Category(3,"شیمی",R.drawable.chemistery_icon),
        Category(4,"نقاشی",R.drawable.drawing_category),
        Category(5,"عکاسی",R.drawable.photography_category),
        Category(6,"فیلم",R.drawable.movie_category),
        Category(7,"زمین شناسی",R.drawable.geology_category),
        Category(8,"صنایع",R.drawable.industrial_category),
        Category(9,"برق",R.drawable.electrical_category),
        Category(10,"عمران",R.drawable.civil_category),
        Category(11,"بازاریابی",R.drawable.marketing_category),
        Category(12,"تناسب اندام",R.drawable.exercise_category),
    )
}