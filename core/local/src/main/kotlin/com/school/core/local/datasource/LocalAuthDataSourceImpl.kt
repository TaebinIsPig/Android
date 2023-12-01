package com.school.core.local.datasource

import com.school.core.data.local.LocalAuthDataSource
import com.school.core.local.preference.AuthPreference
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class LocalAuthDataSourceImpl @Inject constructor(
    private val authPreference: AuthPreference,
) : LocalAuthDataSource {
    override fun saveAccessToken(accessToken: String) =
        authPreference.saveAccessToken(accessToken = accessToken)

    override fun fetchAccessToken(): String? =
        authPreference.fetchAccessToken()

    override fun clearAccessToken() =
        authPreference.clearAccessToken()

    override fun saveRefreshToken(refreshToken: String) =
        authPreference.saveRefreshToken(refreshToken = refreshToken)

    override fun fetchRefreshToken(): String? =
        authPreference.fetchRefreshToken()

    override fun clearRefreshToken() =
        authPreference.clearRefreshToken()

    override fun saveAccessTokenExp(accessTokenExp: String) =
        authPreference.saveAccessTokenExp(accessTokenExp = accessTokenExp)

    override fun fetchAccessTokenExp(): LocalDateTime? =
        authPreference.fetchAccessTokenExp()
            ?.let { LocalDateTime.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")) }

    override fun clearAccessTokenExp() =
        authPreference.clearAccessTokenExp()

    override fun saveRefreshTokenExp(refreshTokenExp: String) =
        authPreference.saveRefreshTokenExp(refreshTokenExp = refreshTokenExp)

    override fun fetchRefreshTokenExp(): LocalDateTime? =
        authPreference.fetchRefreshTokenExp()
            ?.let { LocalDateTime.parse(it, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")) }

    override fun clearRefreshTokenExp() =
        authPreference.clearRefreshTokenExp()
}