package com.example.teachersassistant.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.teachersassistant.data.SubjectDao
import com.example.teachersassistant.data.model.Subject
import kotlinx.coroutines.launch

class AddSubjectViewModel(private val subjectDao: SubjectDao) : ViewModel() {

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
        viewModelScope.launch {
            subjectDao.insertSubject(subject)
        }
    }

    fun isEntryValid(subjectName: String, subjectShortName: String, subjectDay: String): Boolean {
        if (subjectName.isBlank() || subjectShortName.isBlank() || subjectDay.isBlank()) {
            return false
        }
        return true
    }

}

class AddSubjectViewModelFactory(private val subjectDao: SubjectDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddSubjectViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddSubjectViewModel(subjectDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}