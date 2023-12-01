package com.school.feature.signup.phone_number

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.feature.signup.signup.SignupViewModel

@Composable
fun PhoneNumberScreen(
    navigateCertificate: () -> Unit,
    signupViewModel: SignupViewModel,
) {
    val container = signupViewModel.container
    val state = container.stateFlow.collectAsState().value
    var phoneNumber by remember { mutableStateOf(state.phoneNumber) }
    Column {
        SchoolTextField(
            title = "전화번호",
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            hint = "전화번호를 입력해주세요.",
            keyboardType = KeyboardType.Number
        )
        Spacer(modifier = Modifier.height(40.dp))
        SchoolButton(text = "인증하기") {
            signupViewModel.sendCertificate(phoneNumber = phoneNumber)
            navigateCertificate()
        }
    }
}