package com.example.masterdetailmvi.presentation.viewmodels

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.masterdetailmvi.MainActivity
import com.example.masterdetailmvi.domain.model.School
import com.example.masterdetailmvi.domain.services.ISchool
import com.example.masterdetailmvi.presentation.mvi.BaseViewModel
import com.example.masterdetailmvi.presentation.utils.SchoolsPagingSource
import com.example.masterdetailmvi.presentation.viewmodels.contracts.SchoolsLibraryContract.UiState
import com.example.masterdetailmvi.presentation.viewmodels.contracts.SchoolsLibraryContract.UserEvent
import com.example.masterdetailmvi.presentation.viewmodels.contracts.SchoolsLibraryContract.SideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SchoolsLibraryViewModel @Inject constructor(
    private val iSchool: ISchool,
) : BaseViewModel<UiState, UserEvent, SideEffect>() {

    override fun createInitialState(): UiState =
        UiState(
            isLoading = true,
            schools = flowOf(PagingData.empty())
        )

    override fun handleUserEvent(userEvent: UserEvent) {
        when (userEvent) {
            is UserEvent.OnSchoolClicked -> {
                (userEvent.activity as? MainActivity)?.goToDetails(id = userEvent.schoolId)
            }
        }
    }

    fun loadData() {
        setUiState {
            copy(
                isLoading = false,
                schools = Pager(
                    config = PagingConfig(
                        pageSize = Int.MAX_VALUE,
                    ),
                    initialKey = 0,
                    pagingSourceFactory = {
                        SchoolsPagingSource(iSchool = iSchool, limit = 10)
                    }
                ).flow.cachedIn(viewModelScope)
            )
        }
    }
}