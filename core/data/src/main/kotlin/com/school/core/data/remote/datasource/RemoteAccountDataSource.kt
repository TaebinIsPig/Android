package com.school.core.data.remote.datasource

import com.school.core.data.remote.request.account.FindPasswordRequest
import com.school.core.data.remote.response.account.FindIdResponse
import com.school.core.data.remote.response.account.MyProfileResponse

interface RemoteAccountDataSource {
    suspend fun findId(phoneNumber: String): FindIdResponse

    suspend fun findPassword(findPasswordRequest: FindPasswordRequest)

    suspend fun myProfile(): MyProfileResponse
}