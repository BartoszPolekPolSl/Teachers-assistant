package com.example.teachersassistant.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "student_subject", primaryKeys = ["student_id", "subject_id"],
    foreignKeys = [ForeignKey(
        entity = Student::class,
        parentColumns = arrayOf("student_id"),
        childColumns = arrayOf("student_id")
    ),
        ForeignKey(
            entity = Subject::class,
            parentColumns = arrayOf("subject_id"),
            childColumns = arrayOf("subject_id")
        )]
)
data class SubjectStudentLink(
    @ColumnInfo(name = "student_id")
    val studentId: Long,
    @ColumnInfo(name = "subject_id")
    val subjectId: Long
)