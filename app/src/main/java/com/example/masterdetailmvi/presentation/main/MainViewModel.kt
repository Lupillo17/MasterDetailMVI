package com.example.masterdetailmvi.presentation.main

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
class MainViewModel @Inject constructor(
    private val cityRepository: CityRepository,
    private val navController: NavController
) : ViewModel() {

    private val _state = MutableStateFlow<MainViewState>(MainViewState.Loading)
    val state: StateFlow<MainViewState> get() = _state

    fun processIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.LoadCities -> loadCities()
            is MainIntent.CitySelected -> onCitySelected(intent.city)
        }
    }

    private fun loadCities() {
        _state.value = MainViewState.Loading

        viewModelScope.launch {
            try {
                val cityDtos = cityRepository.getCities()
                val cities = cityDtos.toDomainList()
                _state.value = MainViewState.Success(cities)
            } catch (e: Exception) {
                _state.value = MainViewState.Error(e.message ?: "Error desconocido")
            }
        }
    }

    private fun onCitySelected(city: City) {
//        val action = CityListFragmentDirections.actionCityListFragmentToCityDetailFragment(city)
//        navController.navigate(action)
    }

}