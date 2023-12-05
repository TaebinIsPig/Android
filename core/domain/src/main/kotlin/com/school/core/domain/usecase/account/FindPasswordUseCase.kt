package com.school.core.domain.usecase.account

import com.school.core.domain.param.account.FindPasswordParam
import com.school.core.domain.repository.AccountRepository
import javax.inject.Inject

class FindPasswordUseCase @Inject constructor(
    private val accountRepository: AccountRepository,
) {
    suspend operator fun invoke(findPasswordParam: FindPasswordParam) = kotlin.runCatching {
        accountRepository.findPassword(findPasswordParam = findPasswordParam)
    }
}