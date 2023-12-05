package com.school.core.domain.param.auth

data class SignupParam(
    val name: String,
    val studentInfo: StudentInfoParam,
    val id: String,
    val password: String,
    val phoneNumber: String,
    val school: String,
) {
    data class StudentInfoParam(
        val grade: Int,
        val `class`: Int,
        val number: Int,
    )
}