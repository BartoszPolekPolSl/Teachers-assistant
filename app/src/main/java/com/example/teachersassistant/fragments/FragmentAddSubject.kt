package com.example.teachersassistant.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.example.teachersassistant.R
import com.example.teachersassistant.TeachersAssistantApplication
import com.example.teachersassistant.data.viewmodel.AddSubjectViewModel
import com.example.teachersassistant.data.viewmodel.AddSubjectViewModelFactory
import com.example.teachersassistant.databinding.FragmentAddSubjectBinding

class FragmentAddSubject : Fragment() {
    private val days = listOf("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek")
    private val viewModel: AddSubjectViewModel by viewModels {
        AddSubjectViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.subjectDao()
        )
    }
    private var _binding: FragmentAddSubjectBinding? = null
    private val binding get() = _binding!!

    private fun isEntryValid(): Boolean {
        return viewModel.isEntryValid(
            binding.txtInputSubjectName.text.toString(),
            binding.txtInputSubjectShort.text.toString(),
            binding.autoCompleteDay.text.toString(),
        )
    }

    private fun addNewSubject() {
        if (isEntryValid()) {
            viewModel.addNewSubject(
                binding.txtInputSubjectName.text.toString(),
                binding.txtInputSubjectShort.text.toString(),
                binding.autoCompleteDay.text.toString(),
                "${binding.timePickerStart.hour}:${binding.timePickerStart.minute}",
                "${binding.timePickerEnd.hour}:${binding.timePickerEnd.minute}"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSubjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, days)
        binding.autoCompleteDay.setAdapter(adapter)
        binding.timePickerStart.setIs24HourView(true)
        binding.timePickerEnd.setIs24HourView(true)

        binding.buttonAddSubject.setOnClickListener {
            addNewSubject()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}