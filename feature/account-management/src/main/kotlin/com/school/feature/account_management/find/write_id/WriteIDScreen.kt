package com.school.feature.account_management.find.write_id

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
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.SchoolTextField

@Composable
fun WriteIDScreen(
    navigateFindPw: () -> Unit,
) {
    var id by remember { mutableStateOf("") }
    Column {
        SchoolTextField(
            title = "아이디",
            value = id,
            onValueChange = { id = it },
            hint = "아이디를 입력해주세요.",
        )
        Spacer(modifier = Modifier.height(40.dp))
        SchoolButton(
            text = "넘어가기",
            activate = id.isNotEmpty()
        ) {
            navigateFindPw()
        }
    }
}