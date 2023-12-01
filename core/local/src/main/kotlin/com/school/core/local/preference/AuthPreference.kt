package com.school.core.local.preference

interface AuthPreference {
    fun saveAccessToken(accessToken: String)

    fun fetchAccessToken(): String?

    fun clearAccessToken()

    fun saveRefreshToken(refreshToken: String)

    fun fetchRefreshToken(): String?

    fun clearRefreshToken()

    fun saveAccessTokenExp(accessTokenExp: String)

    fun fetchAccessTokenExp(): String?

    fun clearAccessTokenExp()

    fun saveRefreshTokenExp(refreshTokenExp: String)

    fun fetchRefreshTokenExp(): String?

    fun clearRefreshTokenExp()
}