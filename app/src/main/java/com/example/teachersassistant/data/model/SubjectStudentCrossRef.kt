package com.example.teachersassistant.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["student_id", "subject_id"])
data class SubjectStudentCrossRef(
    @ColumnInfo(name = "student_id")
    val studentId: Long,
    @ColumnInfo(name = "subject_id")
    val subjectId: Int
)