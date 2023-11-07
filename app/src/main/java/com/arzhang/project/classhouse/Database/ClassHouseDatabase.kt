package com.arzhang.project.classhouse.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.arzhang.project.classhouse.Database.model.Article
import com.arzhang.project.classhouse.Database.model.Course
import com.arzhang.project.classhouse.Database.model.CourseUnit
import com.arzhang.project.classhouse.Local.Converters
import com.arzhang.project.classhouse.Local.genCourse

@Database(entities = [Article::class, Course::class, CourseUnit::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ClassHouseDatabase : RoomDatabase() {

    abstract fun classHouseDao(): ClassHouseDao

    companion object {
        @Volatile
        private var instance: ClassHouseDatabase? = null

        fun getDatabase(context: Context): ClassHouseDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, ClassHouseDatabase::class.java, "classhouse_database")
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            val courses = genCourse()
                            courses.forEach { course ->
                                db.execSQL(
                                    "INSERT INTO course(id,name,image,Category,publishedDate,isFav) VALUES" +
                                            " ('${course.id}','${course.name}','${course.image}','${1}','${course.isFav}')"
                                )
                            }
                        }
                    })
                    .build()
            }
        }
    }
}