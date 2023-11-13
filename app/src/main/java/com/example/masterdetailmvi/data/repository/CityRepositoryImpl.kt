package com.example.masterdetailmvi.data.repository

import com.example.masterdetailmvi.data.model.CityDto
import com.example.masterdetailmvi.domain.repository.CityRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.isSuccess

class CityRepositoryImpl(private val httpClient: HttpClient):CityRepository {
    override suspend fun getCities(): List<CityDto> {
        val response = httpClient.get("https://api.example.com/users")
        if (response.status.isSuccess()) {
            return response.body()
        }
        return emptyList()
    }
}