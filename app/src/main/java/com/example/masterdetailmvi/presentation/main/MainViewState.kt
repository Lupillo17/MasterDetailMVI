package com.example.masterdetailmvi.presentation.main

import com.example.masterdetailmvi.domain.model.City

sealed class MainViewState {
    object Loading : MainViewState()
    data class Success(val cities: List<City>) : MainViewState()
    data class Error(val errorMessage: String) : MainViewState()
}