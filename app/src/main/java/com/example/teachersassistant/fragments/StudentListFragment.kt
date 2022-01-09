package com.example.teachersassistant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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
import com.example.teachersassistant.databinding.StudentListFragmentBinding

import androidx.appcompat.app.AppCompatActivity


class StudentListFragment : Fragment() {
    private val navigationArgs: StudentListFragmentArgs by navArgs()
    private val viewModel: StudentsListViewModel by viewModels {
        StudentListViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.teachersAssistantDao(),
            navigationArgs.subjectId
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
        val adapter = StudentListAdapter(
            {
                val action =
                    StudentListFragmentDirections.actionStudentListFragmentToFragmentAddGrade(
                        it.studentId,
                        navigationArgs.subjectId
                    )
                this.findNavController().navigate(action)
            },
            {
                val action =
                    StudentListFragmentDirections.actionStudentListFragmentToGradeListFragment(
                        it.studentId,
                        navigationArgs.subjectId,
                        "${it.firstName} ${it.lastName}",
                        it.idCardNumber
                    )
                this.findNavController().navigate(action)
            }
        )
        binding.recyclerviewStudents.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerviewStudents.adapter = adapter
        viewModel.studentList.observe(viewLifecycleOwner) {
            if (it.students.isEmpty()) {
                Toast.makeText(
                    context,
                    "Brak studentów! Dodaj w zakładce KONFIGURACJA i wróć tutaj.",
                    Toast.LENGTH_LONG
                ).show()
            }

            adapter.submitList(it.students)
        }
        val sideNavBarAdapter = SideNavBarListAdapter(sideNavBarViewModel.allSubjects) {
            val action =
                StudentListFragmentDirections.actionStudentListFragmentSelf(
                    it.subjectId,
                    it.name,
                    it.day,
                    "${it.startTime} - ${it.endTime}"
                )
            this.findNavController().navigate(action)
        }
        binding.recyclerviewSideNavbar.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerviewSideNavbar.adapter = sideNavBarAdapter
        sideNavBarViewModel.allSubjects.observe(viewLifecycleOwner) {
            adapter.notifyDataSetChanged()
        }

        binding.txtViewSubjectDay.text =
            "${navigationArgs.subjectDay}: ${navigationArgs.subjectTime}"


        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            navigationArgs.subjectName


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}