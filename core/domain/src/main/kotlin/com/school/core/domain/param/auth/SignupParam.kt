package com.school.core.domain.param.auth

import com.school.core.domain.entity.school.SchoolEntity

data class SignupParam(
    val name: String,
    val studentInfo: StudentInfoParam,
    val id: String,
    val password: String,
    val phoneNumber: String,
    val school: SchoolEntity,
) {
    data class StudentInfoParam(
        val grade: Int,
        val `class`: Int,
        val number: Int,
    )
}