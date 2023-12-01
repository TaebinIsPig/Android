package com.school.core.domain.usecase.auth

import com.school.core.domain.param.SignupParam
import com.school.core.domain.repository.AuthRepository
import javax.inject.Inject

class SignupUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(signupParam: SignupParam) = kotlin.runCatching {
        authRepository.signup(signupParam = signupParam)
    }
}