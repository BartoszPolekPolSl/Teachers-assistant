package com.example.teachersassistant.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time

@Entity(tableName = "subjects_table")
data class Subject(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "subject_id")
    val subjectId: Long = 0L,
    val name: String,
    @ColumnInfo(name = "short_name")
    val shortName: String,
    val day: String,
    @ColumnInfo(name = "start_time")
    val startTime: String,
    @ColumnInfo(name = "end_time")
    val endTime: String

)