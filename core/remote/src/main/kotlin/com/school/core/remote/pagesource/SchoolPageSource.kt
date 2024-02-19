package com.school.core.remote.pagesource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.school.core.data.remote.response.school.SchoolResponse
import com.school.core.remote.api.SchoolAPI

class SchoolPageSource(
    private val schoolAPI: SchoolAPI,
    private val schoolName: String,
) : PagingSource<Int, SchoolResponse>() {
    override fun getRefreshKey(state: PagingState<Int, SchoolResponse>): Int? =
        state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SchoolResponse> {
        return try {
            val page = params.key ?: 1
            val response = schoolAPI.searchSchool(page = page, schoolName = schoolName)

            LoadResult.Page(
                data = response.schoolList,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (response.isLast) null else page + 1
            )
        } catch (e: Exception) {
            println("안녕 $e")
            LoadResult.Error(e)
        }
    }
}