package com.school.profile.profile

import com.school.core.domain.entity.account.MyProfileEntity

data class ProfileState(
    val editProfileVisible: Boolean = false,
    val myProfileEntity: MyProfileEntity = MyProfileEntity(
        name = "",
        phoneNumber = "",
        school = "",
        studentInfo = MyProfileEntity.StudentInfoEntity(
            grade = 1,
            `class` = 1,
            number = 1
        )
    ),
)