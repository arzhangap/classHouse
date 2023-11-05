package com.arzhang.project.classhouse.Database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.arzhang.project.classhouse.Local.Converters
import java.sql.Date

@Entity(tableName = "course")
data class Course(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val image: String,
    @ColumnInfo(name="category_id")
    val categoryId: Int,
//    @ColumnInfo(name="published_date")
//    val publishedDate: Date,
    @ColumnInfo(name="is_fav")
    var isFav: Boolean = false,
)

data class Category(
    val id: Int,
    val name: String
)

