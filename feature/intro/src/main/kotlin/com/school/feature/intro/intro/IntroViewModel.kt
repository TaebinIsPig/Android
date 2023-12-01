package com.school.feature.intro.intro

import androidx.lifecycle.ViewModel
import com.school.core.domain.usecase.auth.IsSignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class IntroViewModel @Inject constructor(
    private val isSignInUseCase: IsSignInUseCase,
) : ViewModel(), ContainerHost<IntroState, IntroSideEffect> {
    override val container = container<IntroState, IntroSideEffect>(IntroState())

    fun isSignIn() = intent {
        isSignInUseCase().onSuccess {
            postSideEffect(IntroSideEffect.AlreadySignIn)
        }.onFailure {
            reduce { state.copy(isNeedLogin = true) }
        }
    }
}