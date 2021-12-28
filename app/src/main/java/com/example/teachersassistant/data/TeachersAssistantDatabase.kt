package com.example.teachersassistant.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.teachersassistant.data.model.Grade
import com.example.teachersassistant.data.model.Student
import com.example.teachersassistant.data.model.Subject
import com.example.teachersassistant.data.model.SubjectStudentLink

@Database(
    entities = [Grade::class, Subject::class, Student::class, SubjectStudentLink::class],
    version = 4,
    exportSchema = false
)
abstract class TeachersAssistantDatabase : RoomDatabase() {
    abstract fun teachersAssistantDao(): TeachersAssistantDao

    companion object {
        @Volatile
        private var INSTANCE: TeachersAssistantDatabase? = null

        fun getDatabase(context: Context): TeachersAssistantDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeachersAssistantDatabase::class.java,
                    "teachers_assistant_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}
