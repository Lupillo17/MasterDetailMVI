package com.example.masterdetailmvi.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.masterdetailmvi.R
import com.example.masterdetailmvi.databinding.FragmentSchoolDetailsBinding
import com.example.masterdetailmvi.presentation.viewmodels.SchoolDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SchoolDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSchoolDetailsBinding
    private val schoolDetailsViewModel: SchoolDetailsViewModel by viewModels()
    private val args: SchoolDetailsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpActionBar()
        binding = FragmentSchoolDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            schoolDetailsViewModel.uiState.collect { uiState ->
                binding.uiState = uiState
            }
        }
        args.schoolId?.let { schoolDetailsViewModel.loadData(it) }
    }

    private fun setUpActionBar() {
        requireActivity().title = getString(R.string.school_details)
    }
}