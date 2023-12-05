package com.school.core.data.remote.response.account

import com.google.gson.annotations.SerializedName
import com.school.core.domain.entity.account.FindIdEntity

data class FindIdResponse(
    @SerializedName("id")
    val id: String,
)

fun FindIdResponse.toEntity() = FindIdEntity(
    id = id
)
