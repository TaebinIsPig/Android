package com.school.feature.signin.signin

import androidx.lifecycle.ViewModel
import com.school.core.domain.param.SignInParam
import com.school.core.domain.usecase.auth.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
) : ViewModel(), ContainerHost<Unit, SignInSideEffect> {
    override val container = container<Unit, SignInSideEffect>(Unit)

    fun signIn(id: String, password: String) = intent {
        signInUseCase(SignInParam(id = id, password = password)).onSuccess {
            postSideEffect(SignInSideEffect.Success)
        }.onFailure {
            postSideEffect(SignInSideEffect.Error(it.message))
        }
    }
}