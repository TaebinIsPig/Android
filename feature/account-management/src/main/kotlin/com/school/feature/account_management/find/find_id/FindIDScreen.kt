package com.school.feature.account_management.find.find_id

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.component.textview.TitleSmallText
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(SchoolTheme.colors.lightGray3)
                .padding(top = 32.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TitleSmallText(
                text = state.id,
                color = SchoolTheme.colors.black,
                fontWeight = FontWeight.SemiBold
            )
            BodyMediumText(text = "아이디를 찾았습니다.", color = SchoolTheme.colors.gray)
        }
        Spacer(modifier = Modifier.height(40.dp))
        SchoolButton(
            text = "넘어가기",
        ) {
            navigateSignIn()
        }
    }
}