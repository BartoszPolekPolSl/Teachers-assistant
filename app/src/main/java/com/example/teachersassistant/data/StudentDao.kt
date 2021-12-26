package com.example.teachersassistant.data

import androidx.room.*
import com.example.teachersassistant.data.model.Student
import com.example.teachersassistant.data.model.SubjectWithStudentsAndGrades

@Dao
interface StudentDao {
    @Insert
    fun insertStudent(student: Student)

    @Delete
    fun deleteStudent(student: Student)
}