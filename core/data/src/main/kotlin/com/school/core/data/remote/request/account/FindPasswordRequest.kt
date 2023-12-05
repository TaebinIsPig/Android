package com.school.core.data.remote.request.account

import com.google.gson.annotations.SerializedName
import com.school.core.domain.param.account.FindPasswordParam

data class FindPasswordRequest(
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("newPassword")
    val newPassword: String,
)

fun FindPasswordParam.toRequest() = FindPasswordRequest(
    phoneNumber = phoneNumber,
    id = id,
    newPassword = newPassword
)
