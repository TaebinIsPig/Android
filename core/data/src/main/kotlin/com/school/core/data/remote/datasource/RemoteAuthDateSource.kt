package com.school.core.data.remote.datasource

import com.school.core.data.remote.request.auth.SignInRequest
import com.school.core.data.remote.request.auth.SignupRequest
import com.school.core.data.remote.response.auth.TokenResponse

interface RemoteAuthDateSource {
    suspend fun signup(signupRequest: SignupRequest)

    suspend fun sendCertificate(phoneNumber: String)

    suspend fun verifyCertificate(authCode: Int, phoneNumber: String)

    suspend fun signIn(signInRequest: SignInRequest): TokenResponse
}