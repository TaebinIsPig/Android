package com.school.core.remote.api

import com.school.core.data.remote.request.account.FindPasswordRequest
import com.school.core.data.remote.response.account.FindIdResponse
import com.school.core.data.remote.response.account.MyProfileResponse
import com.school.core.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path

interface AccountAPI {
    @GET("${EndPoint.account}/find/id/phone-number/{phoneNumber}")
    suspend fun findId(
        @Path("phoneNumber") phoneNumber: String,
    ): FindIdResponse

    @PATCH("${EndPoint.account}/find/password")
    suspend fun findPassword(
        @Body findPasswordRequest: FindPasswordRequest,
    ): Response<Void?>

    @GET("${EndPoint.account}/profile")
    suspend fun myProfile(): MyProfileResponse
}