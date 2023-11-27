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

    fun saveSignInfo(id: String, password: String) = intent {
        reduce { state.copy(id = id, password = password) }
    }

    fun savePhoneNumber(phoneNumber: String) = intent {
        reduce { state.copy(phoneNumber = phoneNumber) }
    }

    fun saveSchool(school: String) = intent {
        reduce { state.copy(school = school) }
    }
}