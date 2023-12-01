package com.school.core.domain.usecase.auth

import com.school.core.domain.param.SignInParam
import com.school.core.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(signInParam: SignInParam) = kotlin.runCatching {
        authRepository.signIn(signInParam = signInParam)
    }
}