package com.school.core.remote.interceptor

import com.google.gson.Gson
import com.school.core.data.local.LocalAuthDataSource
import com.school.core.data.remote.exception.ExpiredTokenException
import com.school.core.data.remote.exception.FailRefreshException
import com.school.core.data.remote.exception.NeedSignInException
import com.school.core.data.remote.response.auth.TokenResponse
import com.school.core.remote.BuildConfig
import com.school.core.remote.util.EndPoint
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.time.LocalDateTime
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val localAuthDataSource: LocalAuthDataSource,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val path = request.url.encodedPath
        if (path.startsWith("/${EndPoint.auth}")) return chain.proceed(request)
        else if (path.contains("find")) return chain.proceed(request)
        else if (path.contains("search")) return chain.proceed(request)
        val now = LocalDateTime.now()
        val accessExpiredAt =
            localAuthDataSource.fetchAccessTokenExp()
                ?: throw NeedSignInException()
        val refreshExpiredAt =
            localAuthDataSource.fetchRefreshTokenExp()
                ?: throw NeedSignInException()
        if (now.isAfter(refreshExpiredAt)) {
            with(localAuthDataSource) {
                clearAccessToken()
                clearRefreshToken()
                clearAccessTokenExp()
                clearRefreshTokenExp()
            }
            throw ExpiredTokenException()
        } else if (now.isAfter(accessExpiredAt)) {
            val client = OkHttpClient()
            val refreshRequest = Request.Builder()
                .url("${BuildConfig.BASE_URL}${EndPoint.auth}/reissue")
                .patch("".toRequestBody("application/json".toMediaTypeOrNull()))
                .addHeader("refreshToken", "Bearer ${localAuthDataSource.fetchRefreshToken()}")
                .build()
            val response = client.newCall(refreshRequest).execute()
            if (response.isSuccessful) {
                val loginResponse = Gson().fromJson(
                    response.body!!.string(),
                    TokenResponse::class.java
                )
                with(localAuthDataSource) {
                    saveAccessToken(loginResponse.accessToken)
                    saveRefreshToken(loginResponse.refreshToken)
                    saveAccessTokenExp(loginResponse.accessTokenExp)
                    saveRefreshTokenExp(loginResponse.refreshTokenExp)
                }
            } else throw FailRefreshException()
        }
        return chain.proceed(
            request = request.newBuilder()
                .addHeader(
                    "Authorization",
                    "Bearer ${localAuthDataSource.fetchAccessToken()}"
                ).build()
        )
    }
}