package com.school.feature.account_management.certificate.viewmodel

import com.school.feature.account_management.navigation.AccountManagementType

data class CertificateState(
    val phoneNumber: String = "",
    val accountManagementType: AccountManagementType = AccountManagementType.Signup,
    val errorMessage: String = "",
)

sealed class CertificateSideEffect {
    data object Success : CertificateSideEffect()

    data class Error(val message: String? = null) : CertificateSideEffect()
}