package com.school.core.domain.repository

import com.school.core.domain.entity.account.FindIdEntity

interface AccountRepository {
    suspend fun findId(phoneNumber: String): FindIdEntity
}