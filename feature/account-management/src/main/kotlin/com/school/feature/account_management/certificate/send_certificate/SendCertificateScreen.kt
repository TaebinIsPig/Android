package com.school.feature.account_management.certificate.send_certificate

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textview.BodySmallText
import com.school.core.ui.util.data.phoneNumberRegexCheck
import com.school.core.ui.util.lifecycle.observeWithLifecycle
import com.school.feature.account_management.certificate.viewmodel.CertificateSideEffect
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun PhoneNumberScreen(
    navigateCertificate: () -> Unit,
    certificateViewModel: CertificateViewModel,
) {
    val container = certificateViewModel.container
    val sideEffect = container.sideEffectFlow
    val state = container.stateFlow.collectAsState().value
    var phoneNumber by remember { mutableStateOf(state.phoneNumber) }
    var phoneNumberErrorText by remember { mutableStateOf(state.errorMessage) }

    sideEffect.observeWithLifecycle {
        when (it) {
            is CertificateSideEffect.Success -> navigateCertificate()
            is CertificateSideEffect.Error -> it.message?.let { phoneNumberErrorText = it }
        }
    }

    Column {
        SchoolTextField(
            title = "전화번호",
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            hint = "전화번호를 입력해주세요.",
            keyboardType = KeyboardType.Number
        )
        if (phoneNumberErrorText.isNotEmpty()) {
            BodySmallText(
                modifier = Modifier.padding(top = 8.dp, bottom = 6.dp, start = 16.dp),
                text = phoneNumberErrorText,
                color = SchoolTheme.colors.error
            )
        } else {
            Spacer(modifier = Modifier.height(40.dp))
        }
        SchoolButton(text = "인증하기", activate = phoneNumber.isNotEmpty()) {
            if (phoneNumber.phoneNumberRegexCheck().not()) {
                phoneNumberErrorText = "전화번호 형식이 아닙니다."
            } else {
                certificateViewModel.sendCertificate(phoneNumber = phoneNumber)
            }
        }
    }
}