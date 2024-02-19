package com.school.core.data.remote.response.school

import com.google.gson.annotations.SerializedName
import com.school.core.domain.entity.school.SchoolEntity

data class SchoolResponse(
    @SerializedName("educationCode")
    val educationCode: String,
    @SerializedName("adminCode")
    val adminCode: String,
    @SerializedName("schoolName")
    val schoolName: String,
    @SerializedName("schoolType")
    val schoolType: String,
)

fun SchoolResponse.toEntity() = SchoolEntity(
    educationCode = educationCode,
    adminCode = adminCode,
    schoolName = schoolName,
    schoolType = schoolType
)