package com.example.teachersassistant.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity


@Entity(primaryKeys = ["student_id", "subject_id"], tableName = "grades_table")
data class Grade(
    @ColumnInfo(name = "student_id")
    val studentId: Long,
    @ColumnInfo(name = "subject_id")
    val subjectId: Int,
    val grade: Float,
    val note: String
)