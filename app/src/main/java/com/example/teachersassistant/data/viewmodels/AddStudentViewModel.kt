package com.example.teachersassistant.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.data.TeachersAssistantDao
import com.example.teachersassistant.data.model.Student
import com.example.teachersassistant.data.model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddStudentViewModel(private val teachersAssistantDao: TeachersAssistantDao) : ViewModel() {

    val allSubjects: LiveData<List<Subject>> = teachersAssistantDao.getAllSubjects()

    fun addNewStudentWithSubject(
        firstName: String,
        lastName: String,
        idCard: String,
        subjectList: MutableList<Subject?>
    ) {
        val newStudent = Student(firstName = firstName, lastName = lastName, cardIdNumber = idCard)
        insertStudentWithSubjects(newStudent, subjectList)
    }


    private fun insertStudentWithSubjects(student: Student, subjectList: MutableList<Subject?>) {
        viewModelScope.launch(Dispatchers.IO) {
            val studentId = teachersAssistantDao.insertStudent(student)
            subjectList.forEach { subject ->
                teachersAssistantDao.insertStudentWithSubject(studentId, subject!!.subjectId)
            }
        }
    }

    fun isEntryValid(
        studentFirstName: String,
        studentLastName: String,
        studentIdCard: String,
        subjectList: MutableList<Subject?>
    ): Boolean {
        if (studentFirstName.isBlank() || studentLastName.isBlank() || studentIdCard.isBlank() || subjectList.size == 0) {
            return false
        }
        return true
    }
}

class AddStudentViewModelFactory(private val teachersAssistantDao: TeachersAssistantDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddStudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddStudentViewModel(teachersAssistantDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}