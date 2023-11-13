package com.example.masterdetailmvi.presentation.list

import com.example.masterdetailmvi.domain.model.City

sealed class CityListIntent{
    object LoadCities : CityListIntent()
    data class CitySelected(val city: City) : CityListIntent()
}
