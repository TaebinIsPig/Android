package com.school.core.domain.param.account

data class FindPasswordParam(
    val phoneNumber: String,
    val id: String,
    val newPassword: String,
)
