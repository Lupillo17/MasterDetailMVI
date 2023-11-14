package com.example.masterdetailmvi.data.implementations

import com.example.masterdetailmvi.data.model.SchoolDTO
import com.example.masterdetailmvi.data.utils.Mapper.toDomain
import com.example.masterdetailmvi.data.utils.SchoolUtilsAPI
import com.example.masterdetailmvi.domain.model.School
import com.example.masterdetailmvi.domain.services.ISchool
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SchoolImpl(
    private val httpClient: HttpClient,
    private val defaultDisPatcher: CoroutineDispatcher = Dispatchers.IO,
) : ISchool {
    override suspend fun getSchools(): List<School> =
        withContext(defaultDisPatcher) {
            httpClient
                .get("")
                .body<List<SchoolDTO>>()
                .map { it.toDomain() }
        }

    override suspend fun getSchoolByPage(limit: Int, page: Int): List<School> =
        withContext(defaultDisPatcher) {
            httpClient
                .get {
                    parameter(SchoolUtilsAPI.LIMIT_PARAM, limit)
                    parameter(SchoolUtilsAPI.PAGE_PARAM, page)
                }.body<List<SchoolDTO>>()
                .map { it.toDomain() }
        }

    override suspend fun getSchoolById(id: String): School =
        withContext(defaultDisPatcher) {
            httpClient
                .get {
                    parameter(SchoolUtilsAPI.ID_PARAM, id)
                }.body<List<SchoolDTO>>()
                .first()
                .toDomain()
        }
}