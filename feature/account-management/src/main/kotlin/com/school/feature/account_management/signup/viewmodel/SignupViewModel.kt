package com.school.feature.account_management.signup.viewmodel

import androidx.lifecycle.ViewModel
import com.school.core.domain.param.auth.SignupParam
import com.school.core.domain.usecase.auth.SignupUseCase
import com.school.core.domain.usecase.school.SearchSchoolUseCase
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
    private val searchSchoolUseCase: SearchSchoolUseCase,
) : ViewModel(), ContainerHost<SignupState, SignupSideEffect> {
    override val container = container<SignupState, SignupSideEffect>(SignupState())

    fun search(schoolName: String) = intent {
        searchSchoolUseCase(schoolName = schoolName).onSuccess {
            reduce { state.copy(schoolPager = it) }
        }.onFailure {
            println("안녕 실패")
        }
    }

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

    fun signup(id: String, password: String, phoneNumber: String) = intent {
        signupUseCase(
            SignupParam(
                name = state.name,
                studentInfo = state.studentInfo,
                id = id,
                password = password,
                phoneNumber = phoneNumber,
                school = state.schoolName
            )
        ).onSuccess {
            postSideEffect(SignupSideEffect.Success)
        }.onFailure {
            postSideEffect(SignupSideEffect.Error(it.message))
        }
    }
}