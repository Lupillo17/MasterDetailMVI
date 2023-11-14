package com.example.masterdetailmvi.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import com.example.masterdetailmvi.domain.services.ISchool
import com.example.masterdetailmvi.presentation.mvi.BaseViewModel
import com.example.masterdetailmvi.presentation.viewmodels.contracts.SchoolDetailsContract.UiState
import com.example.masterdetailmvi.presentation.viewmodels.contracts.SchoolDetailsContract.UserEvent
import com.example.masterdetailmvi.presentation.viewmodels.contracts.SchoolDetailsContract.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
    private val iSchool: ISchool,
) : BaseViewModel<UiState, UserEvent, SideEffect>() {
    override fun createInitialState(): UiState =
        UiState(
            isLoading = true,
            school = null
        )

    override fun handleUserEvent(userEvent: UserEvent) {
        when (userEvent) {
            else -> {}
        }
    }

    fun loadData(schoolId: String) {
        viewModelScope.launch {
            val school = iSchool.getSchoolById(id = schoolId)
            setUiState {
                copy(
                    isLoading = false,
                    school = school
                )
            }
        }
    }
}