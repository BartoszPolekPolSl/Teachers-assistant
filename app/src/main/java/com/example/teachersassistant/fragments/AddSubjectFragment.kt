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
import com.example.teachersassistant.TeachersAssistantApplication
import com.example.teachersassistant.data.viewmodels.AddSubjectViewModel
import com.example.teachersassistant.data.viewmodels.AddSubjectViewModelFactory
import com.example.teachersassistant.databinding.AddSubjectFragmentBinding


class AddSubjectFragment : Fragment() {
    private val days = listOf("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek")
    private val viewModel: AddSubjectViewModel by viewModels {
        AddSubjectViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.teachersAssistantDao()
        )
    }
    private var _binding: AddSubjectFragmentBinding? = null
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
            Toast.makeText(
                context,
                "Pomyślnie dodano przedmiot: ${binding.txtInputSubjectName.text.toString()}",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddSubjectFragmentBinding.inflate(inflater, container, false)
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
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Dodaj przedmiot"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}