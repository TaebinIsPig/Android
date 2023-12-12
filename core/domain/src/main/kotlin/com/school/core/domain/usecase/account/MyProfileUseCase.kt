package com.school.core.domain.usecase.account

import com.school.core.domain.repository.AccountRepository
import javax.inject.Inject

class MyProfileUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke() = kotlin.runCatching {
        accountRepository.myProfile()
    }
}