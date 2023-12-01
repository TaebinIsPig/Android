package com.school.feature.signup.write_sign_info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
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
import com.school.feature.signup.signup.SignupSideEffect
import com.school.feature.signup.signup.SignupViewModel
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun WriteSignInfoScreen(
    navigateMain: () -> Unit,
    signupViewModel: SignupViewModel,
) {
    val container = signupViewModel.container
    val sideEffect = container.sideEffectFlow
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(true) }
    var passwordCheck by remember { mutableStateOf("") }
    var isPasswordCheckVisible by remember { mutableStateOf(true) }
    var idErrorText by remember { mutableStateOf("") }
    var passwordErrorText by remember { mutableStateOf("") }

    sideEffect.observeWithLifecycle {
        when (it) {
            is SignupSideEffect.Success -> navigateMain()
            is SignupSideEffect.Error -> {
                it.message?.let {
                    if (it.contains("아이디")) {
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
            Spacer(modifier = Modifier.height(8.dp))
            BodySmallText(text = idErrorText, color = SchoolTheme.colors.error)
            Spacer(modifier = Modifier.height(6.dp))
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
            Spacer(modifier = Modifier.height(8.dp))
            BodySmallText(text = passwordErrorText, color = SchoolTheme.colors.error)
            Spacer(modifier = Modifier.height(6.dp))
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
            if (password == passwordCheck) {
                signupViewModel.signup(id = id, password = password)
            }
        }
    }
}