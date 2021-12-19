package com.example.teachersassistant

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class FragmentConfiguration:Fragment(R.layout.fragment_configuration) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.findViewById<Button>(R.id.button_add_subject)).setOnClickListener{
            it.findNavController().navigate(R.id.action_fragmentConfiguration_to_fragmentAddSubject)

        }




    }
}