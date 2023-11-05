package com.arzhang.project.classhouse.Database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article")
data class Article (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val content: String,
    val videoUrl: String,
    val unitId: Int
)