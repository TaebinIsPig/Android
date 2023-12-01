package com.school.core.data.remote.datasource

import com.school.core.data.remote.request.auth.SignupRequest

interface RemoteAuthDateSource {
    suspend fun signup(signupRequest: SignupRequest)

    suspend fun sendCertificate(phoneNumber: String)
}