package com.school.feature.account_management.account_management

import androidx.lifecycle.ViewModel
import com.school.core.domain.param.SignupParam
import com.school.core.domain.usecase.auth.SendCertificateUseCase
import com.school.core.domain.usecase.auth.SignupUseCase
import com.school.core.domain.usecase.auth.VerifyCertificateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupUseCase: SignupUseCase,
    private val sendCertificateUseCase: SendCertificateUseCase,
    private val verifyCertificateUseCase: VerifyCertificateUseCase,
) : ViewModel(), ContainerHost<SignupState, SignupSideEffect> {
    override val container = container<SignupState, SignupSideEffect>(SignupState())
    fun saveSchool(schoolName: String) = intent {
        reduce { state.copy(schoolName = schoolName) }
    }

    fun saveStudentInfo(grade: String, `class`: String, number: String, name: String) = intent {
        reduce {
            state.copy(
                name = name,
                studentInfo = SignupParam.StudentInfoParam(
                    grade = grade.toInt(),
                    `class` = `class`.toInt(),
                    number = number.toInt()
                )
            )
        }
    }

    fun sendCertificate(phoneNumber: String) = intent {
        sendCertificateUseCase(phoneNumber = phoneNumber).onSuccess {
            postSideEffect(SignupSideEffect.Success)
            reduce { state.copy(phoneNumber = phoneNumber) }
        }.onFailure {
            postSideEffect(SignupSideEffect.Error(it.message))
        }
    }

    fun verifyCertificate(authCode: String) = intent {
        verifyCertificateUseCase(
            authCode = authCode.toInt(),
            phoneNumber = state.phoneNumber
        ).onSuccess {
            postSideEffect(SignupSideEffect.Success)
        }.onFailure {
            postSideEffect(SignupSideEffect.Error(it.message))
        }
    }

    fun signup(id: String, password: String) = intent {
        signupUseCase(
            SignupParam(
                name = state.name,
                studentInfo = state.studentInfo,
                id = id,
                password = password,
                phoneNumber = state.phoneNumber,
                school = state.schoolName
            )
        ).onSuccess {
            postSideEffect(SignupSideEffect.Success)
        }.onFailure {
            postSideEffect(SignupSideEffect.Error(it.message))
        }
    }
}