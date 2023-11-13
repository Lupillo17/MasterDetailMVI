package com.example.masterdetailmvi.presentation.main

import com.example.masterdetailmvi.domain.model.City

sealed class MainIntent{
    object LoadCities : MainIntent()
    data class CitySelected(val city: City) : MainIntent()
}
