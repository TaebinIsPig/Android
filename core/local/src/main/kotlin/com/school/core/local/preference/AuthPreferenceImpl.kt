package com.school.core.local.preference

import android.content.SharedPreferences
import com.school.core.local.util.clearString
import com.school.core.local.util.fetchString
import com.school.core.local.util.saveString
import javax.inject.Inject

class AuthPreferenceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
) : AuthPreference {
    companion object AuthKey {
        const val ACCESS_TOKEN = "accessToken"
        const val REFRESH_TOKEN = "refreshToken"
        const val ACCESS_TOKEN_EXP = "accessTokenExp"
        const val REFRESH_TOKEN_EXP = "refreshTokenExp"
    }

    override fun saveAccessToken(accessToken: String) =
        sharedPreferences.saveString(ACCESS_TOKEN, accessToken)

    override fun fetchAccessToken(): String? =
        sharedPreferences.fetchString(ACCESS_TOKEN)

    override fun clearAccessToken() =
        sharedPreferences.clearString(ACCESS_TOKEN)

    override fun saveRefreshToken(refreshToken: String) =
        sharedPreferences.saveString(REFRESH_TOKEN, refreshToken)

    override fun fetchRefreshToken(): String? =
        sharedPreferences.fetchString(REFRESH_TOKEN)

    override fun clearRefreshToken() =
        sharedPreferences.clearString(REFRESH_TOKEN)

    override fun saveAccessTokenExp(accessTokenExp: String) =
        sharedPreferences.saveString(ACCESS_TOKEN_EXP, accessTokenExp)

    override fun fetchAccessTokenExp(): String? =
        sharedPreferences.fetchString(ACCESS_TOKEN_EXP)

    override fun clearAccessTokenExp() =
        sharedPreferences.clearString(ACCESS_TOKEN_EXP)

    override fun saveRefreshTokenExp(refreshTokenExp: String) =
        sharedPreferences.saveString(REFRESH_TOKEN_EXP, refreshTokenExp)

    override fun fetchRefreshTokenExp(): String? =
        sharedPreferences.fetchString(REFRESH_TOKEN_EXP)

    override fun clearRefreshTokenExp() =
        sharedPreferences.clearString(REFRESH_TOKEN_EXP)
}