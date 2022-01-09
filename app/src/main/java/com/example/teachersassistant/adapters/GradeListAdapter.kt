package com.example.teachersassistant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.data.model.Grade
import com.example.teachersassistant.data.model.Student
import com.example.teachersassistant.databinding.GradeListItemBinding


class GradeListAdapter : ListAdapter<Grade, GradeListAdapter.GradeListViewHolder>(DiffCallback) {

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Grade>() {
            override fun areItemsTheSame(oldItem: Grade, newItem: Grade): Boolean {
                return oldItem.gradeId == newItem.gradeId
            }

            override fun areContentsTheSame(oldItem: Grade, newItem: Grade): Boolean {
                return oldItem == newItem
            }
        }
    }

    class GradeListViewHolder(private var binding: GradeListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(grade: Grade) {
            binding.grade.text = grade.grade.toString()
            binding.note.text = grade.note
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeListViewHolder {
        return GradeListViewHolder(
            GradeListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GradeListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}