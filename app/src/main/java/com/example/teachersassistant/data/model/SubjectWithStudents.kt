package com.example.teachersassistant.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SubjectWithStudents(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subject_id",
        entityColumn = "student_id",
        associateBy = Junction(SubjectStudentLink::class)
    )
    val students: List<Student>
)