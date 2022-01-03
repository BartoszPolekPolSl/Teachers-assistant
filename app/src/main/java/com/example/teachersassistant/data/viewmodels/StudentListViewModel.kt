package com.example.teachersassistant.data.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teachersassistant.data.TeachersAssistantDao
import com.example.teachersassistant.data.model.SubjectWithStudents

class StudentsListViewModel(
    private val teachersAssistantDao: TeachersAssistantDao,
    private val subjectId: Long
) : ViewModel() {
    val studentsList: LiveData<List<SubjectWithStudents>> =
        teachersAssistantDao.getSubjectWithStudents(subjectId)

}

class StudentListViewModelFactory(
    private val teachersAssistantDao: TeachersAssistantDao,
    private val subjectId: Long
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentsListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentsListViewModel(teachersAssistantDao, subjectId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}