package com.school.feature.main.main

import com.school.core.domain.entity.account.MyProfileEntity

data class MainState(
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