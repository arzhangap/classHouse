package com.arzhang.project.classhouse.Repository

import com.arzhang.project.classhouse.database.ClassHouseDao
import com.arzhang.project.classhouse.database.model.Article
import com.arzhang.project.classhouse.database.model.Course
import com.arzhang.project.classhouse.database.model.CourseUnit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


interface CourseRepository {
    fun getLatestCourses() : List<Course>
    fun getUnits(courseId: Int) : List<CourseUnit>
    fun getArticlesForUnit(unitId: Int): List<Article>
    fun getCourse(courseId: Int) : Course
    fun getArticle(articleId: Int) : Article
    suspend fun update(course: Course)
    fun getFavourites() : Flow<List<Course>>
    fun getCategoryCourses(categoryId: Int) : List<Course>
}

class OfflineCourseRepository @Inject constructor(
    private val classHouseDao: ClassHouseDao
) : CourseRepository {
    override fun getLatestCourses(): List<Course> = classHouseDao.getLatestCourses()
    override fun getUnits(courseId: Int): List<CourseUnit> = classHouseDao.getUnits(courseId)
    override fun getArticlesForUnit(unitId: Int): List<Article> = classHouseDao.getArticlesForUnit(unitId)
    override fun getCourse(courseId: Int): Course = classHouseDao.getCourse(courseId = courseId)
    override fun getArticle(articleId: Int): Article = classHouseDao.getArticle(articleId)
    override suspend fun update(course: Course)  = classHouseDao.update(course)
    override fun getFavourites(): Flow<List<Course>> = classHouseDao.getFavCourses()
    override fun getCategoryCourses(categoryId: Int): List<Course> = classHouseDao.getCategoryCourses(categoryId)
}