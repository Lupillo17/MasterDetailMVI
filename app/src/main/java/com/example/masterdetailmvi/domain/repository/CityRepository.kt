package com.example.masterdetailmvi.domain.repository

import com.example.masterdetailmvi.data.model.CityDto

interface CityRepository {
    suspend fun getCities(): List<CityDto>
}