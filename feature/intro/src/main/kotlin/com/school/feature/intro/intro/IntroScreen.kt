package com.school.feature.intro.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.component.textview.FugazOneText
import com.school.core.ui.util.modifier.schoolClickable

@Composable
fun IntroScreen(
    navigateLogin: () -> Unit,
    navigateSignUp: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .offset(x = 119.dp, y = (-60).dp)
                .size(333.dp)
                .background(color = SchoolTheme.colors.pink2, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-97).dp, y = (-60).dp)
                .size(387.dp)
                .background(color = SchoolTheme.colors.main, shape = CircleShape)
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = (-46).dp, y = 150.dp)
                .size(387.dp)
                .background(color = SchoolTheme.colors.pink, shape = CircleShape)
        )
        FugazOneText(
            modifier = Modifier
                .offset(y = 185.dp)
                .align(Alignment.TopCenter),
            text = "school",
            textSize = 50
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SchoolButton(text = "로그인", onClick = navigateLogin)
            Spacer(modifier = Modifier.height(14.dp))
            Row(
                modifier = Modifier.schoolClickable(onClick = navigateSignUp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                BodyMediumText(text = "계정이 없으신가요?", fontWeight = FontWeight.Medium)
                BodyMediumText(text = "회원가입", fontWeight = FontWeight.SemiBold)
                BodyMediumText(text = "하러가기", fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Preview
@Composable
fun PreviewIntroScreen() {
    SchoolTheme {
        IntroScreen(navigateLogin = {}, navigateSignUp = {})
    }
}