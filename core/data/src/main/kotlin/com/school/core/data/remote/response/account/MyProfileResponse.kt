package com.school.core.data.remote.response.account

import com.google.gson.annotations.SerializedName
import com.school.core.domain.entity.account.MyProfileEntity

data class MyProfileResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("school")
    val school: String,
    @SerializedName("studentNumber")
    val studentInfo: StudentInfoResponse,
) {
    data class StudentInfoResponse(
        @SerializedName("grade")
        val grade: Int,
        @SerializedName("classNum")
        val `class`: Int,
        @SerializedName("number")
        val number: Int,
    )
}

fun MyProfileResponse.toEntity() = MyProfileEntity(
    name = name,
    phoneNumber = phoneNumber,
    studentInfo = studentInfo.toEntity(),
    school = school
)

fun MyProfileResponse.StudentInfoResponse.toEntity() = MyProfileEntity.StudentInfoEntity(
    grade = grade,
    `class` = `class`,
    number = number
)
