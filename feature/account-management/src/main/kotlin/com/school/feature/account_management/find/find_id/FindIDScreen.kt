package com.school.feature.account_management.find.find_id

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.util.lifecycle.observeWithLifecycle
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import com.school.feature.account_management.find.viewmodel.FindSideEffect
import com.school.feature.account_management.find.viewmodel.FindViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun FindIDScreen(
    navigateSignIn: () -> Unit,
    popBackStack: () -> Unit,
    findViewModel: FindViewModel,
    certificateViewModel: CertificateViewModel,
) {
    val container = findViewModel.container
    val sideEffect = container.sideEffectFlow
    val state = container.stateFlow.collectAsState().value
    val certificateState = certificateViewModel.container.stateFlow.collectAsState().value

    sideEffect.observeWithLifecycle {
        if (it is FindSideEffect.Error) {
            certificateViewModel.saveErrorMessage(message = it.message ?: "")
            popBackStack()
        }
    }

    LaunchedEffect(Unit) {
        findViewModel.findId(phoneNumber = certificateState.phoneNumber)
    }

    Column {
        SchoolTextField(
            title = "아이디",
            value = state.id,
            onValueChange = { },
            hint = "아이디를 입력해주세요.",
            readOnly = true
        )
        Spacer(modifier = Modifier.height(40.dp))
        SchoolButton(
            text = "넘어가기",
        ) {
            navigateSignIn()
        }
    }
}