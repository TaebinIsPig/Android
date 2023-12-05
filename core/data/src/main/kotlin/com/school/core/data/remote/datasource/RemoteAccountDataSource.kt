package com.school.core.data.remote.datasource

import com.school.core.data.remote.request.account.FindPasswordRequest
import com.school.core.data.remote.response.account.FindIdResponse

interface RemoteAccountDataSource {
    suspend fun findId(phoneNumber: String): FindIdResponse

    suspend fun findPassword(findPasswordRequest: FindPasswordRequest)
}