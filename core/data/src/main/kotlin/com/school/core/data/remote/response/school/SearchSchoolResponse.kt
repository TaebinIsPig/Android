package com.school.core.data.remote.response.school

import com.google.gson.annotations.SerializedName

data class SearchSchoolResponse(
    @SerializedName("educationCode")
    val educationCode: String,
    @SerializedName("adminCode")
    val adminCode: String,
    @SerializedName("schoolName")
    val schoolName: String,
    @SerializedName("schoolType")
    val schoolType: String,
)