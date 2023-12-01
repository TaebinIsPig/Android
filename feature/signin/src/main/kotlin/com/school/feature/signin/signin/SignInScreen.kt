package com.school.feature.signin.signin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.PasswordVisibleIcon
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textview.BodySmallText
import com.school.core.ui.component.textview.FugazOneText
import com.school.core.ui.util.lifecycle.observeWithLifecycle
import com.school.core.ui.util.modifier.schoolClickable
import kotlinx.coroutines.InternalCoroutinesApi

@OptIn(InternalCoroutinesApi::class)
@Composable
fun SignInScreen(
    popBackStack: () -> Unit,
    navigateMain: () -> Unit,
    navigateFindId: () -> Unit,
    navigateFindPw: () -> Unit,
    signInViewModel: SignInViewModel = hiltViewModel(),
) {
    val container = signInViewModel.container
    val sideEffect = container.sideEffectFlow
    var id by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(true) }
    var idErrorText by remember { mutableStateOf("") }
    var passwordErrorText by remember { mutableStateOf("") }

    sideEffect.observeWithLifecycle {
        when (it) {
            is SignInSideEffect.Success -> navigateMain()
            is SignInSideEffect.Error -> {
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

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box {
            SchoolIcon(
                modifier = Modifier
                    .fillMaxWidth(),
                icon = SchoolIconList.SignInTopBackground,
                contentScale = ContentScale.FillWidth
            )
            SchoolIcon(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 20.dp, start = 16.dp)
                    .schoolClickable(onClick = popBackStack),
                icon = SchoolIconList.Back
            )
            FugazOneText(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 88.dp, start = 50.dp),
                text = "SIGN IN",
                textSize = 32
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
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
        Row(
            modifier = Modifier
                .align(End)
                .padding(end = 16.dp),
            verticalAlignment = CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BodySmallText(
                modifier = Modifier.schoolClickable(onClick = navigateFindId),
                text = "아이디 찾기",
                color = SchoolTheme.colors.black
            )
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .height(10.dp)
                    .background(SchoolTheme.colors.lightGray)
            )
            BodySmallText(
                modifier = Modifier.schoolClickable(onClick = navigateFindPw),
                text = "비밀번호 찾기",
                color = SchoolTheme.colors.black
            )
        }
        Spacer(modifier = Modifier.height(18.dp))
        SchoolButton(text = "확인하기", activate = id.isNotEmpty() && password.isNotEmpty()) {
            signInViewModel.signIn(id = id, password = password)
        }
    }
}