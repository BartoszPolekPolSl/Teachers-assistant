package com.example.teachersassistant.fragments


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.teachersassistant.TeachersAssistantApplication
import com.example.teachersassistant.adapters.GradeListAdapter
import com.example.teachersassistant.data.viewmodels.GradeListViewModel
import com.example.teachersassistant.data.viewmodels.GradeListViewModelFactory
import com.example.teachersassistant.databinding.GradeListFragmentBinding
import com.example.teachersassistant.databinding.StudentListFragmentBinding


class GradeListFragment : Fragment() {
    private val navigationArg: GradeListFragmentArgs by navArgs()
    private val viewModel: GradeListViewModel by viewModels {

        GradeListViewModelFactory(
            (activity?.application as TeachersAssistantApplication).database.teachersAssistantDao(),
            navigationArg.subjectId,
            navigationArg.studentId
        )
    }

    private var _binding: GradeListFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GradeListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = GradeListAdapter()
        binding.recyclerViewGrades.layoutManager = LinearLayoutManager(this.context)
        binding.recyclerViewGrades.adapter = adapter
        viewModel.gradeList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            binding.txtViewMean.text = String.format("%.2f", viewModel.getGradeMean(it) ?: 0f)
            binding.txtViewHighestGrade.text = viewModel.getHighestGrade(it)?.toString() ?: ""
            binding.txtViewLowestGrade.text = viewModel.getLowestGrade(it)?.toString() ?: ""
            if (viewModel.isRisk(it) == null) {
                binding.txtViewRisk.text = ""
            } else {
                if (viewModel.isRisk(it)!!) {
                    binding.txtViewRisk.text = "TAK"
                } else {
                    binding.txtViewRisk.text = "NIE"
                }
            }

        }
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            "${navigationArg.studentName} - ${navigationArg.studentIdCard}"

    }
}