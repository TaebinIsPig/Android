package com.school.feature.account_management.find.find_id

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.SchoolTextField

@Composable
fun FindIDScreen(
    navigateSignIn: () -> Unit,
) {
    Column {
        SchoolTextField(
            title = "아이디",
            value = "",
            onValueChange = { },
            hint = "아이디를 입력해주세요.",
            readOnly = true
        )
        Spacer(modifier = Modifier.height(40.dp))
        SchoolButton(
            text = "넘어가기",
        ) {
            navigateSignIn()
        }
    }
}