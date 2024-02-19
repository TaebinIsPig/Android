package com.school.core.remote.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.school.core.data.remote.datasource.RemoteSchoolDataSource
import com.school.core.data.remote.response.school.SchoolResponse
import com.school.core.remote.api.SchoolAPI
import com.school.core.remote.pagesource.SchoolPageSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteSchoolDataSourceImpl @Inject constructor(
    private val schoolAPI: SchoolAPI,
) : RemoteSchoolDataSource {
    override suspend fun searchSchool(
        schoolName: String,
    ): Flow<PagingData<SchoolResponse>> = Pager(config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            SchoolPageSource(schoolAPI = schoolAPI, schoolName = schoolName)
        }).flow
}