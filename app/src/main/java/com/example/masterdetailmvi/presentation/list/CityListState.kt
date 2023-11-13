package com.example.masterdetailmvi.presentation.list

import com.example.masterdetailmvi.domain.model.City

sealed class CityListState {
    object Loading : CityListState()
    data class Success(val cities: List<City>) : CityListState()
    data class Error(val errorMessage: String) : CityListState()
}