package com.example.teachersassistant.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import com.example.teachersassistant.data.model.Subject

@Dao
interface SubjectDao {
    @Insert
    suspend fun insertSubject(subject: Subject)

    @Delete
    suspend fun deleteSubject(subject: Subject)
}