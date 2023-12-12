package com.school.core.domain.entity.account

data class MyProfileEntity(
    val name: String,
    val phoneNumber: String,
    val school: String,
    val studentInfo: StudentInfoEntity,
) {
    data class StudentInfoEntity(
        val grade: Int,
        val `class`: Int,
        val number: Int,
    )
}