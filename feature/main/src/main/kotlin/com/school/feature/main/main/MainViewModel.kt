package com.school.feature.main.main

import androidx.lifecycle.ViewModel
import com.school.core.domain.usecase.account.MyProfileUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val myProfileUseCase: MyProfileUseCase,
) : ViewModel(), ContainerHost<MainState, Unit> {
    override val container = container<MainState, Unit>(MainState())

    fun myProfile() = intent {
        myProfileUseCase().onSuccess {
            reduce { state.copy(myProfileEntity = it) }
        }
    }
}