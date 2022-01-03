package com.example.teachersassistant.data.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teachersassistant.data.TeachersAssistantDao
import com.example.teachersassistant.data.model.Subject

class GradeBookViewModel(private val teachersAssistantDao: TeachersAssistantDao) : ViewModel() {
    val allSubjects: LiveData<List<Subject>> = teachersAssistantDao.getAllSubjects()

}

class GradeBookViewModelFactory(private val teachersAssistantDao: TeachersAssistantDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradeBookViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GradeBookViewModel(teachersAssistantDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}