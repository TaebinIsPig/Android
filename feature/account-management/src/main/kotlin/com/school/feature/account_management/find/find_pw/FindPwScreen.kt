package com.school.feature.account_management.find.find_pw

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
import com.school.core.ui.component.background.SchoolBackground
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.PasswordVisibleIcon
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textview.BodySmallText
import com.school.core.ui.util.data.pwLengthCheck
import com.school.core.ui.util.data.pwRegexCheck
import com.school.core.ui.util.lifecycle.observeWithLifecycle
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import com.school.feature.account_management.find.viewmodel.FindSideEffect
import com.school.feature.account_management.find.viewmodel.FindViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay

@OptIn(InternalCoroutinesApi::class)
@Composable
fun FindPwScreen(
    navigateSignIn: () -> Unit,
    popBackStack: () -> Unit,
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
    var isSuccess: Boolean? by remember { mutableStateOf(null) }

    sideEffect.observeWithLifecycle {
        when (it) {
            FindSideEffect.Success -> {
                isSuccess = true
                delay(1000)
                navigateSignIn()
            }

            is FindSideEffect.Error -> {
                isSuccess = false
                it.message?.let {
                    if (it.contains("비밀번호"))
                        passwordErrorText = it
                    else {
                        delay(1000)
                        certificateViewModel.saveErrorMessage(message = it)
                        popBackStack()
                    }
                }
                delay(1000)
                isSuccess = null
            }
        }
    }

    SchoolBackground(isSuccess = isSuccess) {
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
            } else if (password.pwLengthCheck().not()) {
                passwordErrorText = "비밀번호의 글자 수는 8 ~ 20자 사이여야 합니다."
            } else if (password.pwRegexCheck().not()) {
                passwordErrorText = "비밀번호는 대소문자와 특수문자를 한개씩 포함하여야 합니다."
            } else {
                findViewModel.findPassword(
                    phoneNumber = certificateState.phoneNumber,
                    newPassword = password
                )
            }
        }
    }
}