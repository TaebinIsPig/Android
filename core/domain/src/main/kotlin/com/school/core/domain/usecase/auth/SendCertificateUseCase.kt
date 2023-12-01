package com.school.core.domain.usecase.auth

import com.school.core.domain.repository.AuthRepository
import javax.inject.Inject

class SendCertificateUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(phoneNumber: String) = kotlin.runCatching {
        authRepository.sendCertificate(phoneNumber = phoneNumber)
    }
}