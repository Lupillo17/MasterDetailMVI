package com.example.masterdetailmvi.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.masterdetailmvi.R
import com.example.masterdetailmvi.databinding.FragmentSchoolsLibraryBinding
import com.example.masterdetailmvi.presentation.fragments.adapters.SchoolsLibraryAdapter
import com.example.masterdetailmvi.presentation.viewmodels.SchoolsLibraryViewModel
import com.example.masterdetailmvi.presentation.viewmodels.contracts.SchoolsLibraryContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SchoolsLibraryFragment: Fragment() {
    private lateinit var binding: FragmentSchoolsLibraryBinding
    private lateinit var adapter: SchoolsLibraryAdapter
    private val schoolsLibraryViewModel: SchoolsLibraryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setUpActionBar()
        setUpAdapter()
        binding = FragmentSchoolsLibraryBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.adapter = adapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            schoolsLibraryViewModel.uiState.collect { uiState ->
                binding.uiState = uiState
                uiState.schools.collectLatest { schools ->
                    adapter.submitData(pagingData = schools)
                }
            }
        }
        schoolsLibraryViewModel.loadData()
    }

    private fun setUpActionBar() {
        requireActivity().title = getString(R.string.schools_library)
    }

    private fun setUpAdapter() {
        adapter = SchoolsLibraryAdapter(
            onSchoolClicked = { schoolId ->
                schoolsLibraryViewModel.handleUserEvent(
                    userEvent = SchoolsLibraryContract.UserEvent.OnSchoolClicked(
                        navController = findNavController(),
                        schoolId = schoolId
                    )
                )
            }
        )
    }
}