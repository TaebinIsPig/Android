package com.school.core.domain.usecase.account

import com.school.core.domain.repository.AccountRepository
import javax.inject.Inject

class FindIdUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(phoneNumber: String) = kotlin.runCatching {
        accountRepository.findId(phoneNumber = phoneNumber)
    }
}