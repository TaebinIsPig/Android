package com.school.core.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("accessTokenExpiredAt")
    val accessTokenExp: String,
    @SerializedName("refreshTokenExpiredAt")
    val refreshTokenExp: String,
)