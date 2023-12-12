package com.school.core.data.repository

import com.school.core.data.remote.datasource.RemoteAccountDataSource
import com.school.core.data.remote.request.account.toRequest
import com.school.core.data.remote.response.account.toEntity
import com.school.core.domain.entity.account.FindIdEntity
import com.school.core.domain.entity.account.MyProfileEntity
import com.school.core.domain.param.account.FindPasswordParam
import com.school.core.domain.repository.AccountRepository
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val remoteAccountDataSource: RemoteAccountDataSource,
) : AccountRepository {
    override suspend fun findId(phoneNumber: String): FindIdEntity =
        remoteAccountDataSource.findId(phoneNumber = phoneNumber).toEntity()

    override suspend fun findPassword(findPasswordParam: FindPasswordParam) =
        remoteAccountDataSource.findPassword(findPasswordRequest = findPasswordParam.toRequest())

    override suspend fun myProfile(): MyProfileEntity =
        remoteAccountDataSource.myProfile().toEntity()
}