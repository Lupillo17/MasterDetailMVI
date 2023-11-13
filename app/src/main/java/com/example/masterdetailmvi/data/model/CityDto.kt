package com.example.masterdetailmvi.data.model

import com.example.masterdetailmvi.domain.model.City

data class CityDto(val name: String)

fun CityDto.toDomain(): City {
    return City(
        name = this.name
    )
}

fun List<CityDto>.toDomainList(): List<City> {
    return this.map { it.toDomain() }
}
