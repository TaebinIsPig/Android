package com.school.feature.account_management.signup.viewmodel

import com.school.core.domain.param.SignupParam

data class SignupState(
    val schoolName: String = "",
    val studentInfo: SignupParam.StudentInfoParam = SignupParam.StudentInfoParam(
        grade = 0,
        `class` = 0,
        number = 0
    ),
    val name: String = "",
)

sealed class SignupSideEffect {
    data object Success : SignupSideEffect()

    data class Error(val message: String? = null) : SignupSideEffect()
}