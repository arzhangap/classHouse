package com.arzhang.project.classhouse.Database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("unit")
data class Unit(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo("unit_number")
    val unitNumber: Int,
    val name: String,
    @ColumnInfo("course_id")
    val courseId: Int
)