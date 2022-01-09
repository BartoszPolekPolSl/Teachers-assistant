package com.example.teachersassistant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.set
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.TeachersAssistantApplication
import com.example.teachersassistant.adapters.AddStudentListAdapter
import com.example.teachersassistant.data.viewmodels.AddStudentViewModelFactory
import com.example.teachersassistant.data.viewmodels.AddStudentViewModel
import com.example.teachersassistant.databinding.AddStudentFragmentBinding


class AddStudentFragment : Fragment() {
    private val viewModel: AddStudentViewModel by viewModels {

        AddStudentViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.teachersAssistantDao()
        )
    }
    private lateinit var adapter: AddStudentListAdapter
    private var _binding: AddStudentFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddStudentFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = AddStudentListAdapter(viewModel.allSubjects)
        binding.recyclerViewSubjects.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewSubjects.adapter = adapter
        viewModel.allSubjects.observe(viewLifecycleOwner,
            { adapter.notifyDataSetChanged() }
        )
        binding.buttonAddStudent.setOnClickListener {
            addNewStudentWithSubjects()
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Dodaj studenta"
    }

    private fun addNewStudentWithSubjects() {
        if (isEntryValid()) {
            var selectedSubjects = adapter.getSelectedStudents()
            viewModel.addNewStudentWithSubject(
                binding.txtInputStudentFirstName.text.toString(),
                binding.txtInputStudentLastName.text.toString(),
                binding.txtInputStudentIdCard.text.toString(),
                selectedSubjects
            )
            Toast.makeText(
                context,
                "Pomyślnie dodano użytkownika: ${binding.txtInputStudentFirstName.text.toString()} ${binding.txtInputStudentLastName.text.toString()}",
                Toast.LENGTH_LONG
            ).show()
            cleanForm()
        }
    }

    private fun cleanForm() {
        binding.txtInputStudentFirstName.setText("")
        binding.txtInputStudentLastName.setText("")
        binding.txtInputStudentIdCard.setText("")
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.txtInputStudentFirstName.text.toString(),
            binding.txtInputStudentLastName.text.toString(),
            binding.txtInputStudentIdCard.text.toString(),
            adapter.getSelectedStudents()
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}