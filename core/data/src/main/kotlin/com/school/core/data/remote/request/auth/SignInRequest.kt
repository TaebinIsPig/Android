package com.school.core.data.remote.request.auth

import com.google.gson.annotations.SerializedName
import com.school.core.domain.param.auth.SignInParam

data class SignInRequest(
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
)

fun SignInParam.toRequest() = SignInRequest(
    id = id,
    password = password
)