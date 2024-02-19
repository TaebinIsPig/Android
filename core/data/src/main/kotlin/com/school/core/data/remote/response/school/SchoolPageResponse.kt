package com.school.core.data.remote.response.school

import com.google.gson.annotations.SerializedName

data class SchoolPageResponse(
    @SerializedName("isLast")
    val isLast: Boolean,
    @SerializedName("schoolList")
    val schoolList: List<SchoolResponse>,
)