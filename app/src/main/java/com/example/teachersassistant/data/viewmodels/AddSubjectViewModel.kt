package com.example.teachersassistant.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.data.TeachersAssistantDao
import com.example.teachersassistant.data.model.Subject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddSubjectViewModel(private val teachersAssistantDao: TeachersAssistantDao) : ViewModel() {

    fun addNewSubject(
        subjectName: String,
        subjectShortName: String,
        subjectDay: String,
        subjectStartTime: String,
        subjectEndTime: String
    ) {
        val newSubject = Subject(
            name = subjectName,
            shortName = subjectShortName,
            day = subjectDay,
            startTime = subjectStartTime,
            endTime = subjectEndTime
        )
        insertSubject(newSubject)
    }

    private fun insertSubject(subject: Subject) {
        viewModelScope.launch(Dispatchers.IO) {
            teachersAssistantDao.insertSubject(subject)
        }
    }

    fun isEntryValid(subjectName: String, subjectShortName: String, subjectDay: String): Boolean {
        if (subjectName.isBlank() || subjectShortName.isBlank() || subjectDay.isBlank()) {
            return false
        }
        return true
    }

}

class AddSubjectViewModelFactory(private val teachersAssistantDao: TeachersAssistantDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddSubjectViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddSubjectViewModel(teachersAssistantDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}