package com.school.core.data.local

import java.time.LocalDateTime

interface LocalAuthDataSource {
    fun saveAccessToken(accessToken: String)

    fun fetchAccessToken(): String?

    fun clearAccessToken()

    fun saveRefreshToken(refreshToken: String)

    fun fetchRefreshToken(): String?

    fun clearRefreshToken()

    fun saveAccessTokenExp(accessTokenExp: String)

    fun fetchAccessTokenExp(): LocalDateTime?

    fun clearAccessTokenExp()

    fun saveRefreshTokenExp(refreshTokenExp: String)

    fun fetchRefreshTokenExp(): LocalDateTime?

    fun clearRefreshTokenExp()
}