package com.example.teachersassistant.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.teachersassistant.R

class ConfigurationFragment : Fragment(R.layout.configuration_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.findViewById<Button>(R.id.button_add_subject)).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentConfiguration_to_fragmentAddSubject)
        }
        (view.findViewById<Button>(R.id.button_add_student)).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentConfiguration_to_fragmentAddStudent)
        }




    }
}