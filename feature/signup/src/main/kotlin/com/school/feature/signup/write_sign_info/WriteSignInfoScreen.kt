package com.school.feature.signup.write_sign_info

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
import androidx.compose.ui.unit.dp
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.PasswordVisibleIcon
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.feature.signup.signup.SignupViewModel

@Composable
fun WriteSignInfoScreen(
    navigatePhoneNumber: () -> Unit,
    signupViewModel: SignupViewModel,
) {
    val container = signupViewModel.container
    val state = container.stateFlow.collectAsState().value
    var id by remember { mutableStateOf(state.id) }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(true) }
    var passwordCheck by remember { mutableStateOf("") }
    var isPasswordCheckVisible by remember { mutableStateOf(true) }
    Column {
        SchoolTextField(
            title = "아이디",
            value = id,
            onValueChange = { id = it },
            hint = "아이디를 입력해주세요."
        )
        Spacer(modifier = Modifier.height(28.dp))
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
        Spacer(modifier = Modifier.height(21.dp))
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
        SchoolButton(text = "넘어가기") {
            signupViewModel.saveSignInfo(id = id, password = password)
            navigatePhoneNumber()
        }
    }
}