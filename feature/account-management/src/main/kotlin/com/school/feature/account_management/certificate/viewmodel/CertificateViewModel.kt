package com.school.feature.account_management.certificate.viewmodel

import androidx.lifecycle.ViewModel
import com.school.core.domain.usecase.auth.SendCertificateUseCase
import com.school.core.domain.usecase.auth.VerifyCertificateUseCase
import com.school.feature.account_management.navigation.AccountManagementType
import com.school.feature.account_management.signup.viewmodel.SignupSideEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class CertificateViewModel @Inject constructor(
    private val sendCertificateUseCase: SendCertificateUseCase,
    private val verifyCertificateUseCase: VerifyCertificateUseCase,
) : ViewModel(), ContainerHost<CertificateState, CertificateSideEffect> {
    override val container = container<CertificateState, CertificateSideEffect>(CertificateState())

    fun saveAccountManagementType(accountManagementType: AccountManagementType) = intent {
        reduce { state.copy(accountManagementType = accountManagementType) }
    }

    fun sendCertificate(phoneNumber: String) = intent {
        sendCertificateUseCase(phoneNumber = phoneNumber).onSuccess {
            postSideEffect(CertificateSideEffect.Success)
            reduce { state.copy(phoneNumber = phoneNumber) }
        }.onFailure {
            postSideEffect(CertificateSideEffect.Error(it.message))
        }
    }

    fun verifyCertificate(authCode: String) = intent {
        verifyCertificateUseCase(
            authCode = authCode.toInt(),
            phoneNumber = state.phoneNumber
        ).onSuccess {
            postSideEffect(CertificateSideEffect.Success)
        }.onFailure {
            postSideEffect(CertificateSideEffect.Error(it.message))
        }
    }
}