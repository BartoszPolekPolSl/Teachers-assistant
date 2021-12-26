package com.example.teachersassistant

import android.app.Application
import com.example.teachersassistant.data.TeachersAssistantDatabase

class TeachersAssistantApplication : Application() {
    val database: TeachersAssistantDatabase by lazy { TeachersAssistantDatabase.getDatabase(this) }
}