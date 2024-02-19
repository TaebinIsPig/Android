package com.school.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.school.core.data.remote.datasource.RemoteSchoolDataSource
import com.school.core.data.remote.response.school.toEntity
import com.school.core.domain.entity.school.SchoolEntity
import com.school.core.domain.repository.SchoolRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(
    private val remoteSchoolDataSource: RemoteSchoolDataSource,
) : SchoolRepository {
    override suspend fun searchSchool(schoolName: String): Flow<PagingData<SchoolEntity>> =
        remoteSchoolDataSource.searchSchool(schoolName = schoolName)
            .map { it.map { it.toEntity() } }
}