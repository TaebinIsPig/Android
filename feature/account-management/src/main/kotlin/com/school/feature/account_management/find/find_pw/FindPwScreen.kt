package com.school.feature.account_management.find.find_pw

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

@Composable
fun FindPwScreen(
    navigateSignIn: () -> Unit,
) {
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(true) }
    var passwordCheck by remember { mutableStateOf("") }
    var isPasswordCheckVisible by remember { mutableStateOf(true) }
    var passwordErrorText by remember { mutableStateOf("") }

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
            activate = password.isNotEmpty() && passwordCheck.isNotEmpty()
        ) {
            if (password != passwordCheck) {
                passwordErrorText = "두 비밀번호가 일치하지 않습니다."
            } else {
                navigateSignIn()
            }
        }
    }
}