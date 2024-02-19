package com.school.feature.account_management.signup.viewmodel

import androidx.paging.PagingData
import com.school.core.domain.entity.school.SchoolEntity
import com.school.core.domain.param.auth.SignupParam
import kotlinx.coroutines.flow.Flow

data class SignupState(
    val schoolPager: Flow<PagingData<SchoolEntity>>? = null,
    val school: SchoolEntity? = null,
    val studentInfo: SignupParam.StudentInfoParam = SignupParam.StudentInfoParam(
        grade = 0,
        `class` = 0,
        number = 0
    ),
    val name: String = "",
)

sealed class SignupSideEffect {
    data object Success : SignupSideEffect()

    data class Error(val message: String? = null) : SignupSideEffect()
}