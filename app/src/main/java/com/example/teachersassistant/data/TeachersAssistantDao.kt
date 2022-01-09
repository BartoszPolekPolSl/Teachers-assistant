package com.example.teachersassistant.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.teachersassistant.data.model.*

@Dao
interface TeachersAssistantDao {
    /*@Transaction
    @Query("SELECT * FROM grades_table")
    fun getSubjectWithStudentsAndGrades(): List<SubjectWithStudentsAndGrades>*/
    @Insert
    suspend fun insertStudent(student: Student): Long

    @Insert
    suspend fun insertGrade(grade: Grade)

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

    @Query("SELECT * FROM grades_table WHERE student_id=:studentId AND subject_id=:subjectId")
    fun getStudentGrades(studentId: Long, subjectId: Long): LiveData<List<Grade>>

    @Query("SELECT * FROM students_table WHERE student_id=:studentId")
    fun getStudent(studentId: Long): LiveData<List<Student>>

    @Transaction
    @Query("SELECT * FROM subjects_table WHERE subject_id=:subjectId")
    fun getSubjectWithStudents(subjectId: Long): LiveData<SubjectWithStudents>
}