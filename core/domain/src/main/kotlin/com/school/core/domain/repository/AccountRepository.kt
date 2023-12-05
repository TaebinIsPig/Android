package com.school.core.domain.repository

import com.school.core.domain.entity.account.FindIdEntity
import com.school.core.domain.param.account.FindPasswordParam

interface AccountRepository {
    suspend fun findId(phoneNumber: String): FindIdEntity

    suspend fun findPassword(findPasswordParam: FindPasswordParam)
}