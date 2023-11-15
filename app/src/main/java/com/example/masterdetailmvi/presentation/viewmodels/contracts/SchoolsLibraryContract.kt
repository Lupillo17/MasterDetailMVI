package com.example.masterdetailmvi.presentation.viewmodels.contracts

import androidx.navigation.NavController
import androidx.paging.PagingData
import com.example.masterdetailmvi.domain.model.School
import com.example.masterdetailmvi.presentation.mvi.BaseSideEffect
import com.example.masterdetailmvi.presentation.mvi.BaseUiState
import com.example.masterdetailmvi.presentation.mvi.BaseUserEvent
import kotlinx.coroutines.flow.Flow

sealed class SchoolsLibraryContract {
    data class UiState(
        val isLoading: Boolean,
        val schools: Flow<PagingData<School>>,
    ) : BaseUiState

    sealed class UserEvent : BaseUserEvent {
        data class OnSchoolClicked(
            val navController: NavController,
            val schoolId: String,
        ) : UserEvent()
    }

    sealed class SideEffect : BaseSideEffect
}