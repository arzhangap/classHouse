package com.arzhang.project.classhouse.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article (
    @PrimaryKey
    val id: Int,
    val name: String,
    val content: String,
    @ColumnInfo(name="video_url")
    val videoUrl: String,
    val date: String,
    @ColumnInfo(name="unit_id")
    val unitId: Int
)