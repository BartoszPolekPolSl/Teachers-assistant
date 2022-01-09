package com.example.teachersassistant.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.teachersassistant.R
import com.example.teachersassistant.TeachersAssistantApplication
import com.example.teachersassistant.data.viewmodels.AddGradeViewModel
import com.example.teachersassistant.data.viewmodels.AddGradeViewModelFactory
import com.example.teachersassistant.data.viewmodels.AddStudentViewModel
import com.example.teachersassistant.data.viewmodels.AddStudentViewModelFactory
import com.example.teachersassistant.databinding.AddGradeFragmentBinding
import com.example.teachersassistant.databinding.AddStudentFragmentBinding

class AddGradeFragment : Fragment() {
    private val grades = listOf(2, 3, 3.5, 4, 4.5, 5)

    private val viewModel: AddGradeViewModel by viewModels {

        AddGradeViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.teachersAssistantDao(),
            navigationArgs.studentId
        )
    }

    private val navigationArgs: AddGradeFragmentArgs by navArgs()

    private var _binding: AddGradeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddGradeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val arrayAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, grades)
        viewModel.student.observe(viewLifecycleOwner) {
            val student = it.get(0)
            bindStudentInfo(student.firstName, student.lastName, student.idCardNumber)
        }
        binding.autoCompleteGrade.setAdapter(arrayAdapter)
        binding.buttonAddGrade.setOnClickListener { addGrade() }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Dodaj ocenę"
    }

    private fun addGrade() {
        if (isEntryValid()) {
            viewModel.addGrade(
                navigationArgs.studentId,
                navigationArgs.subjectId,
                binding.autoCompleteGrade.text.toString().toFloat(),
                binding.txtInputNote.text.toString()
            )
            Toast.makeText(
                context,
                "Pomyślnie dodano ocenę: ${binding.txtViewFirstName.text} ${binding.txtViewLastname.text}, Ocena: ${binding.autoCompleteGrade.text}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.autoCompleteGrade.text.toString(),
            binding.txtInputNote.text.toString(),
        )
    }

    private fun bindStudentInfo(
        studentFirstName: String,
        studentLastName: String,
        studentIdCard: String
    ) {
        binding.txtViewFirstName.text = studentFirstName
        binding.txtViewLastname.text = studentLastName
        binding.txtViewIdCardNumber.text = studentIdCard
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}