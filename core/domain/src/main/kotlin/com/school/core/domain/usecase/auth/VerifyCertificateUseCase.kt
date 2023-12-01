package com.school.core.domain.usecase.auth

import com.school.core.domain.repository.AuthRepository
import javax.inject.Inject

class VerifyCertificateUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(authCode: Int, phoneNumber: String) = kotlin.runCatching {
        authRepository.verifyCertificate(authCode = authCode, phoneNumber = phoneNumber)
    }
}