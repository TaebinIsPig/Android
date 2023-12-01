package com.school.core.data.remote.request.auth

import com.google.gson.annotations.SerializedName
import com.school.core.domain.param.SignupParam

data class SignupRequest(
    @SerializedName("name")
    val name: String,
    @SerializedName("studentNumber")
    val studentInfo: StudentInfoRequest,
    @SerializedName("id")
    val id: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String,
    @SerializedName("school")
    val school: String,
) {
    data class StudentInfoRequest(
        @SerializedName("grade")
        val grade: Int,
        @SerializedName("classNum")
        val `class`: Int,
        @SerializedName("number")
        val number: Int,
    )
}

fun SignupParam.toRequest() = SignupRequest(
    name = name,
    studentInfo = studentInfo.toRequest(),
    id = id,
    password = password,
    phoneNumber = phoneNumber,
    school = school
)

fun SignupParam.StudentInfoParam.toRequest() = SignupRequest.StudentInfoRequest(
    grade = grade,
    `class` = `class`,
    number = number
)