package com.school.core.domain.repository

import androidx.paging.PagingData
import com.school.core.domain.entity.school.SchoolEntity
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    suspend fun searchSchool(schoolName: String): Flow<PagingData<SchoolEntity>>
}