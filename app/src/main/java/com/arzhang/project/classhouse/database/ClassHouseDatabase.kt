package com.arzhang.project.classhouse.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arzhang.project.classhouse.Local.Converters
import com.arzhang.project.classhouse.database.model.Article
import com.arzhang.project.classhouse.database.model.Course
import com.arzhang.project.classhouse.database.model.CourseUnit

@Database(entities = [Article::class, Course::class, CourseUnit::class], version = 1, exportSchema = true)
@TypeConverters(Converters::class)
abstract class ClassHouseDatabase : RoomDatabase() {

    abstract fun classHouseDao(): ClassHouseDao

    companion object {
        @Volatile
        private var instance: ClassHouseDatabase? = null

        fun getDatabase(context: Context): ClassHouseDatabase {
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, ClassHouseDatabase::class.java, "classhouse.db")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("class.db")
                    .build()
            }
        }
    }
}