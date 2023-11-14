package com.example.masterdetailmvi.presentation.viewmodels.contracts

import com.example.masterdetailmvi.domain.model.School
import com.example.masterdetailmvi.presentation.mvi.BaseSideEffect
import com.example.masterdetailmvi.presentation.mvi.BaseUiState
import com.example.masterdetailmvi.presentation.mvi.BaseUserEvent

sealed class SchoolDetailsContract {
    data class UiState(
        val isLoading: Boolean,
        val school: School?,
    ) : BaseUiState

    sealed class UserEvent : BaseUserEvent

    sealed class SideEffect : BaseSideEffect
}