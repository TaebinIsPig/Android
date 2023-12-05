package com.school.core.data.repository

import com.school.core.data.local.LocalAuthDataSource
import com.school.core.data.remote.datasource.RemoteAuthDateSource
import com.school.core.data.remote.request.auth.toRequest
import com.school.core.data.remote.response.auth.TokenResponse
import com.school.core.domain.exception.NeedTokenException
import com.school.core.domain.param.auth.SignInParam
import com.school.core.domain.param.auth.SignupParam
import com.school.core.domain.repository.AuthRepository
import java.time.LocalDateTime
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val localAuthDataSource: LocalAuthDataSource,
    private val remoteAuthDateSource: RemoteAuthDateSource,
) : AuthRepository {
    override suspend fun signup(signupParam: SignupParam) =
        remoteAuthDateSource.signup(signupRequest = signupParam.toRequest())

    override suspend fun sendCertificate(phoneNumber: String) =
        remoteAuthDateSource.sendCertificate(phoneNumber = phoneNumber)

    override suspend fun verifyCertificate(authCode: Int, phoneNumber: String) =
        remoteAuthDateSource.verifyCertificate(authCode = authCode, phoneNumber = phoneNumber)

    override suspend fun signIn(signInParam: SignInParam) =
        remoteAuthDateSource.signIn(signInRequest = signInParam.toRequest()).saveToken()

    override suspend fun isSignIn() {
        val now = LocalDateTime.now()
        val refreshToken = localAuthDataSource.fetchRefreshToken() ?: throw NeedTokenException()
        val refreshTokenExp =
            localAuthDataSource.fetchRefreshTokenExp() ?: throw NeedTokenException()
        if (now.isAfter(refreshTokenExp)) {
            with(localAuthDataSource) {
                clearAccessToken()
                clearRefreshToken()
                clearAccessTokenExp()
                clearRefreshTokenExp()
            }
            throw NeedTokenException()
        } else {
            remoteAuthDateSource.refresh(refreshToken = refreshToken).saveToken()
        }
    }

    private fun TokenResponse.saveToken() = with(localAuthDataSource) {
        saveAccessToken(accessToken)
        saveRefreshToken(refreshToken)
        saveAccessTokenExp(accessTokenExp = accessTokenExp)
        saveRefreshTokenExp(refreshTokenExp = refreshTokenExp)
    }
}

