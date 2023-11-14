package com.example.masterdetailmvi.data.utils

import com.example.masterdetailmvi.data.model.SchoolDTO
import com.example.masterdetailmvi.domain.model.School

object Mapper {
    fun SchoolDTO.toDomain(): School =
        School(
            id = id,
            name = name,
            description = description
        )
}