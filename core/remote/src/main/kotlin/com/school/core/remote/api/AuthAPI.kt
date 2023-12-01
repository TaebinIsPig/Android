package com.school.core.remote.api

import com.school.core.data.remote.request.auth.SignupRequest
import com.school.core.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface AuthAPI {
    @POST("${EndPoint.auth}/signup")
    suspend fun signup(
        @Body signupRequest: SignupRequest,
    ): Response<Void?>

    @POST("${EndPoint.auth}/send/phone-number/{phoneNumber}")
    suspend fun sendCertificate(
        @Path("phoneNumber") phoneNumber: String,
    ): Response<Void?>

    @GET("${EndPoint.auth}/auth-code/{authCode}/phone-number/{phoneNumber}")
    suspend fun verifyCertificate(
        @Path("authCode") authCode: Int,
        @Path("phoneNumber") phoneNumber: String,
    ): Response<Void?>
}