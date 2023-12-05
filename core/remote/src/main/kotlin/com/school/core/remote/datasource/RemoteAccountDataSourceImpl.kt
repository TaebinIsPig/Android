package com.school.core.remote.datasource

import com.school.core.data.remote.datasource.RemoteAccountDataSource
import com.school.core.data.remote.response.account.FindIdResponse
import com.school.core.data.remote.util.schoolApiCall
import com.school.core.remote.api.AccountAPI
import javax.inject.Inject

class RemoteAccountDataSourceImpl @Inject constructor(
    private val accountAPI: AccountAPI,
) : RemoteAccountDataSource {
    override suspend fun findId(phoneNumber: String): FindIdResponse = schoolApiCall {
        accountAPI.findId(phoneNumber = phoneNumber)
    }
}