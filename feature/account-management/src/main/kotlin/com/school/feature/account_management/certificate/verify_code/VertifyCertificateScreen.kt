package com.school.feature.account_management.certificate.verify_code

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.component.textview.BodySmallText
import com.school.core.ui.component.textview.HeadText
import com.school.core.ui.util.lifecycle.observeWithLifecycle
import com.school.feature.account_management.certificate.viewmodel.CertificateSideEffect
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import com.school.feature.account_management.navigation.AccountManagementType
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun CertificateScreen(
    popBackStack: () -> Unit,
    navigateWriteSignInfo: () -> Unit,
    navigateFindId: () -> Unit,
    navigateWriteId: () -> Unit,
    certificateViewModel: CertificateViewModel,
) {
    val container = certificateViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sideEffect = container.sideEffectFlow
    var certificateNumber by remember { mutableStateOf("") }
    var certificateNumberErrorText by remember { mutableStateOf("") }

    sideEffect.observeWithLifecycle {
        when (it) {
            is CertificateSideEffect.Success -> when (state.accountManagementType) {
                AccountManagementType.Signup -> navigateWriteSignInfo()
                AccountManagementType.FindID -> navigateFindId()
                AccountManagementType.FindPW -> navigateWriteId()
                AccountManagementType.ChangeNumber -> popBackStack()
                AccountManagementType.ChangeSchool -> {}
            }

            is CertificateSideEffect.Error -> it.message?.let { certificateNumberErrorText = it }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CertificateNumberList(certificateNumber) {
            certificateNumber = it
        }
        Row(
            modifier = Modifier.padding(top = 15.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            BodyMediumText(
                text = "인증번호가 안오셨나요?",
                fontWeight = FontWeight.Medium,
                color = SchoolTheme.colors.black
            )
            BodyMediumText(
                text = "다시받기",
                fontWeight = FontWeight.Medium,
                color = SchoolTheme.colors.pink3
            )
        }
        if (certificateNumberErrorText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(8.dp))
            BodySmallText(text = certificateNumberErrorText, color = SchoolTheme.colors.error)
            Spacer(modifier = Modifier.height(18.dp))
        } else {
            Spacer(modifier = Modifier.height(40.dp))
        }
        SchoolButton(text = "넘어가기", activate = certificateNumber.isNotEmpty()) {
            certificateViewModel.verifyCertificate(authCode = certificateNumber)
        }
    }
}

@Composable
fun CertificateNumberList(
    value: String,
    onValueChange: (String) -> Unit,
) {
    BasicTextField(
        value = value,
        onValueChange = {
            if (it.length < 5 && it.isEmpty() || it.last().isDigit()) onValueChange(it)
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    ) {
        Box {
            Row(
                modifier = Modifier
                    .padding(horizontal = 34.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                CertificateNumber(
                    modifier = Modifier.weight(1F),
                    number = value.getOrElse(0) { Character.MIN_VALUE }.toString()
                )
                CertificateNumber(
                    modifier = Modifier.weight(1F),
                    number = value.getOrElse(1) { Character.MIN_VALUE }.toString()
                )
                CertificateNumber(
                    modifier = Modifier.weight(1F),
                    number = value.getOrElse(2) { Character.MIN_VALUE }.toString()
                )
                CertificateNumber(
                    modifier = Modifier.weight(1F),
                    number = value.getOrElse(3) { Character.MIN_VALUE }.toString()
                )
            }
        }
    }
}

@Composable
fun CertificateNumber(modifier: Modifier = Modifier, number: String) {
    Box(
        modifier = modifier
            .height(82.dp)
            .border(
                width = 2.dp,
                color = SchoolTheme.colors.lightGray2,
                shape = RoundedCornerShape(10.dp)
            )
    ) {
        HeadText(
            modifier = Modifier.align(Alignment.Center),
            text = number,
            color = SchoolTheme.colors.black
        )
    }
}