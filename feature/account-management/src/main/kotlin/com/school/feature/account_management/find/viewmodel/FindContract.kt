package com.school.feature.account_management.find.viewmodel

import com.school.feature.account_management.signup.viewmodel.SignupSideEffect

data class FindState(
    val id: String = "",
)

sealed class FindSideEffect {
    data object Success : FindSideEffect()

    data class Error(val message: String? = null) : FindSideEffect()
}