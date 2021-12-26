package com.example.teachersassistant.data.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class SubjectWithStudentsAndGrades(
    @Embedded val subject: Subject,
    @Relation(
        parentColumn = "subject_id",
        entityColumn = "student_id",
        associateBy = Junction(Grade::class)
    )
    val grades: List<Grade>
)