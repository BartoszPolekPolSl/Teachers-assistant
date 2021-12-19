package com.example.teachersassistant

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputEditText

class FragmentAddSubject:Fragment(R.layout.fragment_add_subject) {
    private val days = listOf("Poniedziałek", "Wtorek", "Środa", "Czwartek", "Piątek")
    @Override
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, days)
        view.findViewById<AutoCompleteTextView>(R.id.autoComplete_day).setAdapter(adapter)
        view.findViewById<TimePicker>(R.id.timePicker_start).setIs24HourView(true)
        view.findViewById<TimePicker>(R.id.timePicker_end).setIs24HourView(true)


    }
}