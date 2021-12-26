package com.example.teachersassistant.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.teachersassistant.data.model.SubjectWithStudentsAndGrades

@Dao
interface GradeDao {
    @Transaction
    @Query("SELECT * FROM grades_table")
    fun getSubjectWithStudentsAndGrades(): List<SubjectWithStudentsAndGrades>
}