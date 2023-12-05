package com.school.core.data.repository

import com.school.core.data.remote.datasource.RemoteAccountDataSource
import com.school.core.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val remoteAccountDataSource: RemoteAccountDataSource,
) : AccountRepository {
}