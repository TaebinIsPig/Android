package com.school.core.data.repository

import com.school.core.data.remote.datasource.RemoteAuthDateSource
import com.school.core.data.remote.request.auth.toRequest
import com.school.core.domain.param.SignInParam
import com.school.core.domain.param.SignupParam
import com.school.core.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remoteAuthDateSource: RemoteAuthDateSource,
) : AuthRepository {
    override suspend fun signup(signupParam: SignupParam) =
        remoteAuthDateSource.signup(signupRequest = signupParam.toRequest())

    override suspend fun sendCertificate(phoneNumber: String) =
        remoteAuthDateSource.sendCertificate(phoneNumber = phoneNumber)

    override suspend fun verifyCertificate(authCode: Int, phoneNumber: String) =
        remoteAuthDateSource.verifyCertificate(authCode = authCode, phoneNumber = phoneNumber)

    override suspend fun signIn(signInParam: SignInParam) {
        remoteAuthDateSource.signIn(signInRequest = signInParam.toRequest())
    }
}

