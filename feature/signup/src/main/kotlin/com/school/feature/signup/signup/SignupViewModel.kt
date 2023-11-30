package com.school.feature.signup.signup

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
) : ViewModel(), ContainerHost<SignupState, Unit> {
    override val container = container<SignupState, Unit>(SignupState())
    fun saveSchool(schoolName: String) = intent {
        reduce { state.copy(schoolName = schoolName) }
    }

    fun saveStudentInfo(grade: String, `class`: String, number: String, name: String) = intent {
        reduce { state.copy(grade = grade, `class` = `class`, number = number, name = name) }
    }

    fun savePhoneNumber(phoneNumber: String) = intent {
        reduce { state.copy(phoneNumber = phoneNumber) }
    }
}