package com.example.teachersassistant.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teachersassistant.data.model.Student
import com.example.teachersassistant.data.model.Subject
import com.example.teachersassistant.data.model.SubjectWithStudentsAndGrades

@Dao
interface TeachersAssistantDao {
    /*@Transaction
    @Query("SELECT * FROM grades_table")
    fun getSubjectWithStudentsAndGrades(): List<SubjectWithStudentsAndGrades>*/
    @Insert
    suspend fun insertStudent(student: Student): Long

    @Delete
    fun deleteStudent(student: Student)

    @Insert
    suspend fun insertSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)

    @Query("INSERT INTO student_subject VALUES (:studentId,:subjectId)")
    suspend fun insertStudentWithSubject(studentId: Long, subjectId: Long)

    @Query("SELECT * FROM subjects_table")
    fun getAllSubjects(): LiveData<List<Subject>>
}