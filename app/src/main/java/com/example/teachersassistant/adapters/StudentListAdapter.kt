package com.example.teachersassistant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.data.model.Student
import com.example.teachersassistant.data.model.Subject
import com.example.teachersassistant.databinding.StudentListItemBinding


class StudentListAdapter(private val onItemClicked: (Student) -> Unit) :
    ListAdapter<Student, StudentListAdapter.StudentListViewHolder>(DiffCallback) {
    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Student>() {
            override fun areItemsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem.studentId == newItem.studentId
            }

            override fun areContentsTheSame(oldItem: Student, newItem: Student): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class StudentListViewHolder(private var binding: StudentListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(student: Student) {
            binding.txtInputStudentFirstName.text = student.firstName
            binding.txtInputStudentLastName.text = student.lastName
            binding.buttonAddGrade.setOnClickListener { onItemClicked(student) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentListViewHolder {
        val viewHolder = StudentListViewHolder(
            StudentListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return viewHolder
    }

    override fun onBindViewHolder(holder: StudentListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}