package com.example.teachersassistant.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.findNavController
import com.example.teachersassistant.R
import com.example.teachersassistant.TeachersAssistantApplication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class ConfigurationFragment : Fragment(R.layout.configuration_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (view.findViewById<Button>(R.id.button_add_subject)).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentConfiguration_to_fragmentAddSubject)
        }
        (view.findViewById<Button>(R.id.button_add_student)).setOnClickListener {
            it.findNavController().navigate(R.id.action_fragmentConfiguration_to_fragmentAddStudent)
        }
        (view.findViewById<Button>(R.id.button_clear_DB)).setOnClickListener {
            clearDB()
        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Konfiguracja"


    }

    fun clearDB() {
        val builder = AlertDialog.Builder(activity).apply {
            setTitle("Potwierdź czyszczenie")
            setMessage("Czy na pewno chcesz wyczyścić bazę?")
            setPositiveButton("Yes") { dialog, id ->
                GlobalScope.launch {
                    (activity?.application as TeachersAssistantApplication).database.clearAllTables()
                }
                dialog.cancel()
                activity?.viewModelStore?.clear()
            }
            setNegativeButton("No") { dialog, id ->
                dialog.cancel()

            }
        }
        val alert = builder.create()
        alert.show()

    }
}