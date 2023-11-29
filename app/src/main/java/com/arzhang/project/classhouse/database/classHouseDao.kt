package com.arzhang.project.classhouse.database


import androidx.room.Dao
import androidx.room.Query
import com.arzhang.project.classhouse.database.model.Article
import com.arzhang.project.classhouse.database.model.Course
import com.arzhang.project.classhouse.database.model.CourseUnit

@Dao
interface ClassHouseDao {

    @Query("SELECT * FROM course")
    fun getLatestCourses() : List<Course>

    @Query("SELECT * FROM course_unit WHERE course_id = :courseId")
    fun getUnits(courseId: Int) : List<CourseUnit>

    @Query("SELECT * FROM article WHERE unit_id = :unitId")
    fun getArticlesForUnit(unitId: Int) : List<Article>

    @Query("SELECT * FROM course WHERE id = :courseId")
    fun getCourse(courseId: Int) : Course

    @Query("SELECT * FROM article WHERE id = :articleId")
    fun getArticle(articleId: Int) : Article

//    @Query("SELECT * FROM course ORDER BY RAND() ASC LIMIT 10")
//    fun getMostViewedCourses() : List<Course>
//
//    @Query("SELECT * FROM course ORDER BY RAND() ASC LIMIT 10")
//    fun getChosenCourses() : List<Course>

    @Query("SELECT * FROM course WHERE course.is_fav = 1 ORDER BY id ASC")
    fun getFavCourses() : List<Course>

    @Query("SELECT * FROM course WHERE category_id = :category ORDER BY id ASC")
    fun getCategoryCourses(category: Int) : List<Course>

}