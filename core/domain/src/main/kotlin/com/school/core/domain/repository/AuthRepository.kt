package com.school.core.domain.repository

import com.school.core.domain.param.auth.SignInParam
import com.school.core.domain.param.auth.SignupParam

interface AuthRepository {
    suspend fun signup(signupParam: SignupParam)

    suspend fun sendCertificate(phoneNumber: String)

    suspend fun verifyCertificate(authCode: Int, phoneNumber: String)

    suspend fun signIn(signInParam: SignInParam)

    suspend fun isSignIn()
}