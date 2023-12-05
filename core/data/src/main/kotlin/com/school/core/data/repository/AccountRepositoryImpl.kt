package com.school.core.data.repository

import com.school.core.data.remote.datasource.RemoteAccountDataSource
import com.school.core.data.remote.response.account.toEntity
import com.school.core.domain.entity.account.FindIdEntity
import com.school.core.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val remoteAccountDataSource: RemoteAccountDataSource,
) : AccountRepository {
    override suspend fun findId(phoneNumber: String): FindIdEntity =
        remoteAccountDataSource.findId(phoneNumber = phoneNumber).toEntity()
}