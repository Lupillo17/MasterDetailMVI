package com.example.masterdetailmvi.domain.services

import com.example.masterdetailmvi.domain.model.School

interface ISchool {
    suspend fun getSchools(): List<School>
    suspend fun getSchoolByPage(limit: Int, page: Int): List<School>
    suspend fun getSchoolById(id: String): School
}