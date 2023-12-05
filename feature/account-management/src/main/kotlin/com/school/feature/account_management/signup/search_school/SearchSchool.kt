package com.school.feature.account_management.signup.search_school

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textfield.SearchButton
import com.school.core.ui.component.textview.BodyLargeText
import com.school.feature.account_management.signup.viewmodel.SignupViewModel

@Composable
fun SearchSchoolScreen(
    navigatePhoneNumber: () -> Unit,
    signupViewModel: SignupViewModel,
) {
    val container = signupViewModel.container
    val state = container.stateFlow.collectAsState().value
    val isSelectSchool = state.schoolName.isNotEmpty()
    var schoolName by remember { mutableStateOf(state.schoolName) }
    var name by remember { mutableStateOf(state.name) }
    var grade by remember { mutableStateOf(state.studentInfo.grade.let { if (it == 0) "" else it.toString() }) }
    var `class` by remember { mutableStateOf(state.studentInfo.`class`.let { if (it == 0) "" else it.toString() }) }
    var number by remember { mutableStateOf(state.studentInfo.number.let { if (it == 0) "" else it.toString() }) }
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
            Spacer(modifier = Modifier.height(28.dp))
            SchoolTextField(
                title = "이름",
                value = name,
                onValueChange = { name = it },
                hint = "이름을 입력해주세요."
            )
            Row(
                modifier = Modifier.padding(top = 28.dp, start = 16.dp, end = 90.dp),
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
        SchoolButton(
            text = "넘어가기",
            activate = (state.schoolName.isEmpty() && schoolName.isNotEmpty()) || (schoolName.isNotEmpty() && grade.isNotEmpty() && `class`.isNotEmpty() && number.isNotEmpty() && name.isNotEmpty())
        ) {
            if (!isSelectSchool) signupViewModel.saveSchool(schoolName = schoolName)
            else {
                signupViewModel.saveStudentInfo(
                    grade = grade,
                    `class` = `class`,
                    number = number,
                    name = name
                )
                navigatePhoneNumber()
            }
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
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
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