package com.school.feature.account_management.find.find_pw

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
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.PasswordVisibleIcon
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textview.BodySmallText
import com.school.core.ui.util.lifecycle.observeWithLifecycle
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import com.school.feature.account_management.find.viewmodel.FindSideEffect
import com.school.feature.account_management.find.viewmodel.FindViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun FindPwScreen(
    navigateSignIn: () -> Unit,
    findViewModel: FindViewModel,
    certificateViewModel: CertificateViewModel,
) {
    val container = findViewModel.container
    val sideEffect = container.sideEffectFlow
    val certificateState = certificateViewModel.container.stateFlow.collectAsState().value
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(true) }
    var passwordCheck by remember { mutableStateOf("") }
    var isPasswordCheckVisible by remember { mutableStateOf(true) }
    var passwordErrorText by remember { mutableStateOf("") }

    sideEffect.observeWithLifecycle {
        when (it) {
            FindSideEffect.Success -> navigateSignIn()
            is FindSideEffect.Error -> passwordErrorText = it.message ?: ""
        }
    }

    Column {
        SchoolTextField(
            title = "비밀번호",
            value = password,
            onValueChange = { password = it },
            hint = "비밀번호를 입력해주세요.",
            isPasswordVisible = isPasswordVisible,
            tailingIcon = {
                PasswordVisibleIcon(isPasswordVisible = isPasswordVisible) {
                    isPasswordVisible = !isPasswordVisible
                }
            }
        )
        if (passwordErrorText.isNotEmpty()) {
            BodySmallText(
                modifier = Modifier.padding(top = 8.dp, bottom = 6.dp, start = 16.dp),
                text = passwordErrorText,
                color = SchoolTheme.colors.error
            )
        } else {
            Spacer(modifier = Modifier.height(28.dp))
        }
        SchoolTextField(
            title = "비밀번호 확인",
            value = passwordCheck,
            onValueChange = { passwordCheck = it },
            hint = "비밀번호를 다시 한번 입력해주세요.",
            isPasswordVisible = isPasswordCheckVisible,
            tailingIcon = {
                PasswordVisibleIcon(isPasswordVisible = isPasswordCheckVisible) {
                    isPasswordCheckVisible = !isPasswordCheckVisible
                }
            }
        )
        Spacer(modifier = Modifier.height(40.dp))
        SchoolButton(
            text = "넘어가기",
            activate = password.isNotEmpty() && passwordCheck.isNotEmpty()
        ) {
            if (password != passwordCheck) {
                passwordErrorText = "두 비밀번호가 일치하지 않습니다."
            } else {
                findViewModel.findPassword(
                    phoneNumber = certificateState.phoneNumber,
                    newPassword = password
                )
            }
        }
    }
}