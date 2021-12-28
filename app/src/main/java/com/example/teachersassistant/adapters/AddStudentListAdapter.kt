package com.example.teachersassistant.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.data.model.Student
import com.example.teachersassistant.data.model.Subject
import com.example.teachersassistant.databinding.SubjectItemBinding

class AddStudentListAdapter(private val subjects: LiveData<List<Subject>>) :
    RecyclerView.Adapter<AddStudentListAdapter.AddStudentListViewHolder>() {

    private val selectedSubjects: MutableList<Subject?> = mutableListOf()
    fun getSelectedStudents(): MutableList<Subject?> {
        return selectedSubjects
    }

    inner class AddStudentListViewHolder(private val binding: SubjectItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: Subject?) {
            binding.txtSubjectName.text = subject?.name
            binding.checkboxSubject.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    selectedSubjects.add(subject)
                } else {
                    selectedSubjects.remove(subject)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddStudentListViewHolder {
        return AddStudentListViewHolder(
            SubjectItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AddStudentListViewHolder, position: Int) {
        val current = subjects.value?.get(position)
        holder.bind(current)
    }

    override fun getItemCount(): Int {
        return subjects.value?.count() ?: 0
    }
}