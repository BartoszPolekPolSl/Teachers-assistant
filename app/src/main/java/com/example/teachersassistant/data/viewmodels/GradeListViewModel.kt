package com.example.teachersassistant.data.viewmodels


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.teachersassistant.data.TeachersAssistantDao
import com.example.teachersassistant.data.model.Grade
import kotlin.math.roundToInt

class GradeListViewModel(
    private val teachersAssistantDao: TeachersAssistantDao,
    private val subjectId: Long,
    private val studentId: Long
) :
    ViewModel() {
    val gradeList: LiveData<List<Grade>> =
        teachersAssistantDao.getStudentGrades(studentId, subjectId)

    fun getGradeMean(gradeList: List<Grade>): Float? {
        val grades = getGrades(gradeList)
        if (grades.isEmpty()) {
            return null
        } else {
            var sum: Float = 0f
            grades.forEach { sum += it }
            return sum / grades.size.toFloat()
        }

    }

    fun getHighestGrade(gradeList: List<Grade>): Float? {
        val grades = getGrades(gradeList)
        return grades.maxOrNull()
    }

    fun getLowestGrade(gradeList: List<Grade>): Float? {
        val grades = getGrades(gradeList)
        return grades.minOrNull()
    }

    fun isRisk(gradeList: List<Grade>): Boolean? {
        val mean = getGradeMean(gradeList) ?: return null
        return mean <= 3.0
    }

    private fun getGrades(gradeList: List<Grade>): MutableList<Float> {
        val grade = mutableListOf<Float>()

        gradeList.forEach { grade.add(it.grade) }
        return grade
    }
}

class GradeListViewModelFactory(
    private val teachersAssistantDao: TeachersAssistantDao,
    private val subjectId: Long,
    private val studentId: Long
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GradeListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GradeListViewModel(teachersAssistantDao, subjectId, studentId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}