package com.school.feature.signup.signup

import com.school.core.domain.param.SignupParam

data class SignupState(
    val schoolName: String = "",
    val studentInfo: SignupParam.StudentInfoParam = SignupParam.StudentInfoParam(
        grade = 0,
        `class` = 0,
        number = 0
    ),
    val name: String = "",
    val phoneNumber: String = "",
)

sealed class SignupSideEffect {
    data object Success : SignupSideEffect()
}