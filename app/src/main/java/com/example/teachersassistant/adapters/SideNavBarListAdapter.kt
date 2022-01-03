package com.example.teachersassistant.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.teachersassistant.data.model.Subject
import com.example.teachersassistant.databinding.SimpleListItem1Binding


class SideNavBarListAdapter(
    private val subjects: LiveData<List<Subject>>,
    private val onItemClicked: (Subject) -> Unit
) : RecyclerView.Adapter<SideNavBarListAdapter.SideNavBarListViewHolder>() {
    class SideNavBarListViewHolder(private val binding: SimpleListItem1Binding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: Subject?) {
            binding.text1.text = subject?.shortName.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SideNavBarListViewHolder {
        return SideNavBarListViewHolder(
            SimpleListItem1Binding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: SideNavBarListViewHolder, position: Int) {
        val current = subjects.value?.get(position)
        holder.bind(current)
        holder.itemView.setOnClickListener {
            onItemClicked(current!!)
        }
    }


    override fun getItemCount(): Int {
        return subjects.value?.count() ?: 0
    }
}