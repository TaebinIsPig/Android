package com.school.core.remote.api

import com.school.core.data.remote.response.school.SchoolPageResponse
import com.school.core.remote.util.EndPoint.school
import retrofit2.http.GET
import retrofit2.http.Query

interface SchoolAPI {
    @GET("$school/search")
    suspend fun searchSchool(
        @Query("schoolName") schoolName: String,
        @Query("page") page: Int,
    ): SchoolPageResponse
}