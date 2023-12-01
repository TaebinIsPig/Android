package com.school.core.domain.usecase.auth

import com.school.core.domain.repository.AuthRepository
import javax.inject.Inject

class IsSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        authRepository.isSignIn()
    }
}