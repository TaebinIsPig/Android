package com.school.core.remote.datasource

import com.school.core.data.remote.datasource.RemoteAuthDateSource
import com.school.core.data.remote.request.auth.SignInRequest
import com.school.core.data.remote.request.auth.SignupRequest
import com.school.core.data.remote.response.auth.TokenResponse
import com.school.core.remote.api.AuthAPI
import com.school.core.data.remote.util.errorHandling
import com.school.core.data.remote.util.schoolApiCall
import javax.inject.Inject

class RemoteAuthDataSourceImpl @Inject constructor(
    private val authAPI: AuthAPI,
) : RemoteAuthDateSource {
    override suspend fun signup(signupRequest: SignupRequest) = schoolApiCall {
        authAPI.signup(signupRequest = signupRequest).errorHandling()
    }

    override suspend fun sendCertificate(phoneNumber: String) = schoolApiCall {
        authAPI.sendCertificate(phoneNumber = phoneNumber).errorHandling()
    }

    override suspend fun verifyCertificate(authCode: Int, phoneNumber: String) = schoolApiCall {
        authAPI.verifyCertificate(authCode = authCode, phoneNumber = phoneNumber).errorHandling()
    }

    override suspend fun signIn(signInRequest: SignInRequest): TokenResponse = schoolApiCall {
        authAPI.signIn(signInRequest = signInRequest)
    }
}