package com.school.feature.intro.intro

data class IntroState(
    val isNeedLogin: Boolean = false,
)

sealed class IntroSideEffect {
    data object AlreadySignIn : IntroSideEffect()
}