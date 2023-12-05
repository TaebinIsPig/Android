package com.school.feature.account_management.find.viewmodel

import androidx.lifecycle.ViewModel
import com.school.core.domain.usecase.account.FindIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class FindViewModel @Inject constructor(
    private val findIdUseCase: FindIdUseCase,
) : ViewModel(), ContainerHost<FindState, FindSideEffect> {
    override val container = container<FindState, FindSideEffect>(FindState())

    fun findId(phoneNumber: String) = intent {
        findIdUseCase(phoneNumber = phoneNumber).onSuccess {
            reduce { state.copy(id = it.id) }
        }.onFailure {
            postSideEffect(FindSideEffect.Error(it.message))
        }
    }
}