package com.school.feature.signup.search_school

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.TextField
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
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textfield.SearchButton
import com.school.core.ui.component.textview.BodyLargeText
import com.school.feature.signup.signup.SignupViewModel

@Composable
fun SearchSchoolScreen(
    navigateMain: () -> Unit,
    signupViewModel: SignupViewModel,
) {
    val container = signupViewModel.container
    val state = container.stateFlow.collectAsState().value
    val isSelectSchool = state.school.isNotEmpty()
    var schoolName by remember { mutableStateOf("") }
    var grade by remember { mutableStateOf("") }
    var `class` by remember { mutableStateOf("") }
    var number by remember { mutableStateOf("") }
    Column {
        SchoolTextField(
            title = "학교",
            hint = "학교 이름을 입력해주세요.",
            value = schoolName,
            betweenSpace = if (isSelectSchool) 11 else 2,
            onValueChange = { schoolName = it },
            tailingIcon = if (!isSelectSchool) {
                {
                    SearchButton {

                    }
                }
            } else null
        )
        if (isSelectSchool) {
            Row(
                modifier = Modifier.padding(top = 24.dp, start = 16.dp, end = 90.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                WriteSchoolInfoTextField(
                    modifier = Modifier.weight(1F),
                    value = grade,
                    maxLength = 2,
                    onValueChange = { grade = it },
                    hint = "학년"
                )
                WriteSchoolInfoTextField(
                    modifier = Modifier.weight(1F),
                    value = `class`,
                    onValueChange = { `class` = it },
                    hint = "반"
                )
                WriteSchoolInfoTextField(
                    modifier = Modifier.weight(1F),
                    value = number,
                    onValueChange = { number = it },
                    hint = "번호"
                )
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        SchoolButton(text = "넘어가기") {
            if (!isSelectSchool) signupViewModel.saveSchool(school = schoolName)
            else navigateMain()
        }
    }
}

@Composable
fun WriteSchoolInfoTextField(
    modifier: Modifier = Modifier,
    value: String,
    maxLength: Int = 3,
    onValueChange: (String) -> Unit,
    hint: String,
) {
    BasicTextField(
        modifier = modifier
            .border(
                width = 1.dp,
                color = SchoolTheme.colors.lightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(vertical = 7.dp, horizontal = 12.dp),
        value = value,
        onValueChange = { if (it.length < maxLength) onValueChange(it) },
        textStyle = SchoolTheme.typography.bodyLarge.copy(color = SchoolTheme.colors.black)
    ) {
        Box {
            it()
            if (value.isEmpty()) {
                BodyLargeText(text = hint, color = SchoolTheme.colors.gray)
            }
        }
    }
}