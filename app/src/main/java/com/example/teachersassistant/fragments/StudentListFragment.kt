package com.example.teachersassistant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.TeachersAssistantApplication
import com.example.teachersassistant.adapters.SideNavBarListAdapter
import com.example.teachersassistant.adapters.StudentListAdapter
import com.example.teachersassistant.data.viewmodels.GradeBookViewModel
import com.example.teachersassistant.data.viewmodels.GradeBookViewModelFactory
import com.example.teachersassistant.data.viewmodels.StudentListViewModelFactory
import com.example.teachersassistant.data.viewmodels.StudentsListViewModel
import com.example.teachersassistant.databinding.GradeBookFragmentBinding
import com.example.teachersassistant.databinding.StudentListFragmentBinding
import android.R

import androidx.appcompat.app.AppCompatActivity


class StudentListFragment : Fragment() {
    private val navigationArgs: StudentListFragmentArgs by navArgs()
    private val viewModel: StudentsListViewModel by viewModels {
        StudentListViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.teachersAssistantDao(),
            navigationArgs.studentId
        )
    }
    private val sideNavBarViewModel: GradeBookViewModel by activityViewModels {

        GradeBookViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.teachersAssistantDao()
        )
    }

    private var _binding: StudentListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = StudentListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = StudentListAdapter()
        binding.recyclerviewStudents.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerviewStudents.adapter = adapter
        viewModel.studentsList.observe(viewLifecycleOwner) {

            adapter.submitList(it.get(0).students)
        }
        val sideNavBarAdapter = SideNavBarListAdapter(sideNavBarViewModel.allSubjects) {
            val action =
                StudentListFragmentDirections.actionStudentListFragmentSelf(it.subjectId)
            this.findNavController().navigate(action)
        }
        binding.recyclerviewSideNavbar.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerviewSideNavbar.adapter = sideNavBarAdapter
        sideNavBarViewModel.allSubjects.observe(viewLifecycleOwner,
            { adapter.notifyDataSetChanged() }
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}