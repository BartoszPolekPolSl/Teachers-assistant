package com.example.teachersassistant.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.data.TeachersAssistantDao
import com.example.teachersassistant.data.model.Grade
import com.example.teachersassistant.data.model.Student
import com.example.teachersassistant.data.model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGradeViewModel(
    private val teachersAssistantDao: TeachersAssistantDao,
    private val studentId: Long
) : ViewModel() {
    val student: LiveData<List<Student>> = teachersAssistantDao.getStudent(studentId)

    fun addGrade(studentId: Long, subjectId: Long, grade: Float, note: String) {
        val grade = Grade(studentId = studentId, subjectId = subjectId, grade = grade, note = note)
        insertGrade(grade)
    }

    private fun insertGrade(grade: Grade) {
        viewModelScope.launch(Dispatchers.IO) {
            teachersAssistantDao.insertGrade(grade)
        }
    }

    fun isEntryValid(grade: String, note: String): Boolean {
        if (grade.isBlank() || note.isBlank()) {
            return false
        }
        return true
    }
}

class AddGradeViewModelFactory(
    private val teachersAssistantDao: TeachersAssistantDao,
    private val studentId: Long
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddGradeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddGradeViewModel(teachersAssistantDao, studentId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}