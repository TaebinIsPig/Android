package com.school.core.data.remote.response.auth

import com.google.gson.annotations.SerializedName

data class TokenResponse(
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String,
    @SerializedName("accessTokenExp")
    val accessTokenExp: String,
    @SerializedName("refreshTokenExp")
    val refreshTokenExp: String,
)