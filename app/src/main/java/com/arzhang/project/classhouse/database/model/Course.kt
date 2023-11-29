package com.arzhang.project.classhouse.database.model

import androidx.annotation.DrawableRes
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class Course(
    @PrimaryKey
    val id: Int,
    val name: String,
    val image: String,
    @ColumnInfo(name="category_id")
    val categoryId: Int,
    @ColumnInfo(name="is_fav")
    var isFav: Boolean = false,
)

data class Category(
    val id: Int,
    val name: String,
    @DrawableRes
    val image: Int
)

