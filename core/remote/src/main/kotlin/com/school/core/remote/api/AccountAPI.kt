package com.school.core.remote.api

import com.school.core.data.remote.response.account.FindIdResponse
import com.school.core.remote.util.EndPoint
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountAPI {
    @GET("${EndPoint.account}/find/id/phone-number/{phoneNumber}")
    suspend fun findId(
        @Path("phoneNumber") phoneNumber: String,
    ): FindIdResponse
}