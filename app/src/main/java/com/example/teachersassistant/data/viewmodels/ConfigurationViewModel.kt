package com.example.teachersassistant.data.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teachersassistant.data.TeachersAssistantDao

class ConfigurationViewModel(private val teachersAssistantDao: TeachersAssistantDao) : ViewModel()
class ConfigurationViewModelFactory(private val teachersAssistantDao: TeachersAssistantDao) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ConfigurationViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ConfigurationViewModel(teachersAssistantDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}