package com.arzhang.project.classhouse.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("course_unit")
data class CourseUnit(
    @PrimaryKey
    val id: Int,
    @ColumnInfo("unit_number")
    val unitNumber: String,
    val name: String,
    @ColumnInfo("course_id")
    val courseId: Int
)
