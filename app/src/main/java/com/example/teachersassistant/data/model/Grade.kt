package com.example.teachersassistant.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "grades_table",
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
data class Grade(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "grade_id")
    val gradeId: Long = 0L,
    @ColumnInfo(name = "student_id")
    val studentId: Long,
    @ColumnInfo(name = "subject_id")
    val subjectId: Long,
    val grade: Float,
    val note: String
)