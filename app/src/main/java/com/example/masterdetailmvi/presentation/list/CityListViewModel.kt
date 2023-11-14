package com.example.masterdetailmvi.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.masterdetailmvi.data.model.toDomainList
import com.example.masterdetailmvi.domain.model.City
import com.example.masterdetailmvi.domain.repository.CityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CityListViewModel @Inject constructor(
    private val cityRepository: CityRepository
) : ViewModel() {

    private val _state = MutableStateFlow<CityListState>(CityListState.Loading)
    val state: StateFlow<CityListState> get() = _state

    fun processIntent(intent: CityListIntent) {
        when (intent) {
            is CityListIntent.LoadCities -> loadCities()
            is CityListIntent.CitySelected -> onCitySelected(intent.city)
        }
    }

    private fun loadCities() {
        _state.value = CityListState.Loading

        viewModelScope.launch {
            try {
                val cityDtos = cityRepository.getCities()
                val cities = cityDtos.toDomainList()
                _state.value = CityListState.Success(cities)
            } catch (e: Exception) {
                _state.value = CityListState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    private fun onCitySelected(city: City) {
        // navigate to 2nd screen
    }

}