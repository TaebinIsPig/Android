package com.school.feature.account_management.signup.write_sign_info

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
import com.school.core.ui.util.data.idLengthCheck
import com.school.core.ui.util.data.pwLengthCheck
import com.school.core.ui.util.data.pwRegexCheck
import com.school.core.ui.util.lifecycle.observeWithLifecycle
import com.school.feature.account_management.certificate.viewmodel.CertificateViewModel
import com.school.feature.account_management.signup.viewmodel.SignupSideEffect
import com.school.feature.account_management.signup.viewmodel.SignupViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun WriteSignInfoScreen(
    navigateSignIn: () -> Unit,
    popBackStack: () -> Unit,
    signupViewModel: SignupViewModel,
    certificateViewModel: CertificateViewModel,
) {
    val container = signupViewModel.container
    val sideEffect = container.sideEffectFlow
    val certificateState = certificateViewModel.container.stateFlow.collectAsState().value
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(true) }
    var passwordCheck by remember { mutableStateOf("") }
    var isPasswordCheckVisible by remember { mutableStateOf(true) }
    var idErrorText by remember { mutableStateOf("") }
    var passwordErrorText by remember { mutableStateOf("") }

    sideEffect.observeWithLifecycle {
        when (it) {
            is SignupSideEffect.Success -> navigateSignIn()
            is SignupSideEffect.Error -> {
                it.message?.let {
                    if (it.contains("전화번호")) {
                        popBackStack()
                    } else if (it.contains("아이디")) {
                        idErrorText = it
                    } else {
                        passwordErrorText = it
                    }
                }
            }
        }
    }

    Column {
        SchoolTextField(
            title = "아이디",
            value = id,
            onValueChange = { id = it },
            hint = "아이디를 입력해주세요."
        )
        if (idErrorText.isNotEmpty()) {
            BodySmallText(
                modifier = Modifier.padding(top = 8.dp, bottom = 6.dp, start = 16.dp),
                text = idErrorText,
                color = SchoolTheme.colors.error
            )
        } else {
            Spacer(modifier = Modifier.height(28.dp))
        }
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
            activate = id.isNotEmpty() && password.isNotEmpty() && passwordCheck.isNotEmpty()
        ) {
            if (password != passwordCheck) {
                passwordErrorText = "두 비밀번호가 일치하지 않습니다."
            } else if (id.idLengthCheck().not()) {
                idErrorText = "아이디의 글자 수는 8 ~ 15자 사이여야 합니다."
            } else if (password.pwLengthCheck().not()) {
                passwordErrorText = "비밀번호의 글자 수는 8 ~ 20자 사이여야 합니다."
            } else if (password.pwRegexCheck().not()) {
                passwordErrorText = "비밀번호는 대소문자와 특수문자를 한개씩 포함하여야 합니다."
            } else {
                signupViewModel.signup(
                    id = id,
                    password = password,
                    phoneNumber = certificateState.phoneNumber
                )
            }
        }
    }
}