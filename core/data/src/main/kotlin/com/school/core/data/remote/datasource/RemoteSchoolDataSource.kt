package com.school.core.data.remote.datasource

import androidx.paging.PagingData
import com.school.core.data.remote.response.school.SchoolResponse
import kotlinx.coroutines.flow.Flow

interface RemoteSchoolDataSource {
    suspend fun searchSchool(schoolName: String): Flow<PagingData<SchoolResponse>>
}