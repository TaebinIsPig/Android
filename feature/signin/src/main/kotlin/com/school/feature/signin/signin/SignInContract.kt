package com.school.feature.signin.signin

sealed class SignInSideEffect {
    data object Success : SignInSideEffect()

    data class Error(val message: String? = null) : SignInSideEffect()
}