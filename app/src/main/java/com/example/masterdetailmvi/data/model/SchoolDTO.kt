package com.example.masterdetailmvi.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SchoolDTO(
    @SerialName("dbn") val id: String,
    @SerialName("school_name") val name: String,
    @SerialName("overview_paragraph") val description: String
)
