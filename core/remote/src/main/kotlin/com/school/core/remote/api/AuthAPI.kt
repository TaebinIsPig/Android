package com.school.core.remote.api

import com.school.core.data.remote.request.auth.SignInRequest
import com.school.core.data.remote.request.auth.SignupRequest
import com.school.core.data.remote.response.auth.TokenResponse
import com.school.core.remote.util.EndPoint
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
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

    @POST("${EndPoint.auth}/signin")
    suspend fun signIn(
        @Body signInRequest: SignInRequest,
    ): TokenResponse

    @PATCH("${EndPoint.auth}/reissue")
    suspend fun refresh(
        @Header("refreshToken") refreshToken: String,
    ): TokenResponse
}