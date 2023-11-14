package com.example.masterdetailmvi.presentation.utils

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.masterdetailmvi.domain.model.School
import com.example.masterdetailmvi.domain.services.ISchool

class SchoolsPagingSource(
    private val iSchool: ISchool,
    private val limit: Int,
) : PagingSource<Int, School>() {
    override fun getRefreshKey(state: PagingState<Int, School>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.inc() ?: anchorPage?.nextKey?.dec()
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, School> =
        try {
            val nextPageNumber = params.key ?: 1
            val response = iSchool.getSchoolByPage(limit = limit, page = nextPageNumber)
            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isNotEmpty()) nextPageNumber.inc() else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }

}