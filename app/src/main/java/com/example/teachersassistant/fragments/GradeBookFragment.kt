package com.example.teachersassistant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.teachersassistant.TeachersAssistantApplication

import com.example.teachersassistant.adapters.SideNavBarListAdapter

import com.example.teachersassistant.data.viewmodels.GradeBookViewModel
import com.example.teachersassistant.data.viewmodels.GradeBookViewModelFactory

import com.example.teachersassistant.databinding.GradeBookFragmentBinding

import androidx.navigation.fragment.findNavController


class GradeBookFragment : Fragment() {
    private val viewModel: GradeBookViewModel by activityViewModels {

        GradeBookViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.teachersAssistantDao()
        )
    }
    private var _binding: GradeBookFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GradeBookFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = SideNavBarListAdapter(viewModel.allSubjects) {
            val action =
                GradeBookFragmentDirections.actionFragmentGradeBookToStudentListFragment(
                    it.subjectId,
                    it.name,
                    it.day,
                    "${it.startTime} - ${it.endTime}"
                )
            this.findNavController().navigate(action)
        }
        binding.recyclerviewSideNavbar.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerviewSideNavbar.adapter = adapter
        viewModel.allSubjects.observe(viewLifecycleOwner,
            {
                if (it.isNotEmpty()) {
                    val action =
                        GradeBookFragmentDirections.actionFragmentGradeBookToStudentListFragment(
                            it.get(
                                0
                            ).subjectId,
                            it.get(0).name,
                            it.get(0).day,
                            "${it.get(0).startTime} - ${it.get(0).endTime}"
                        )
                    this.findNavController().navigate(action)
                }
                adapter.notifyDataSetChanged()
            }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}