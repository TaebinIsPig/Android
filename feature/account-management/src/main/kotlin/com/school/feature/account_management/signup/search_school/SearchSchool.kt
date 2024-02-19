package com.school.feature.account_management.signup.search_school

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.list.RemoveOverScrollLazyColumn
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textfield.StudentTextField
import com.school.core.ui.component.textfield.TailingButton
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.component.textview.BodySmallText
import com.school.core.ui.util.data.nameLengthCheck
import com.school.core.ui.util.modifier.schoolClickable
import com.school.feature.account_management.signup.viewmodel.SignupViewModel

@Composable
fun SearchSchoolScreen(
    navigatePhoneNumber: () -> Unit,
    popProfile: ((String) -> Unit)?,
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
    var nameErrorText by remember { mutableStateOf("") }
    val schoolPager = state.schoolPager?.collectAsLazyPagingItems()
    val focusManager = LocalFocusManager.current

    Column {
        SchoolTextField(
            title = "학교",
            hint = "학교 이름을 입력해주세요.",
            value = schoolName,
            betweenSpace = if (isSelectSchool) 11 else 2,
            readOnly = isSelectSchool,
            onValueChange = { schoolName = it },
            tailingIcon = if (!isSelectSchool) {
                {
                    TailingButton {
                        signupViewModel.search(schoolName = schoolName)
                    }
                }
            } else null
        )

        if (!isSelectSchool) {
            schoolPager?.let { pager ->
                when (pager.loadState.refresh) {
                    else -> {
                        RemoveOverScrollLazyColumn(
                            modifier = Modifier.heightIn(max = 300.dp)
                        ) {

                            items(pager.itemSnapshotList.items) {
                                SchoolText(schoolName = it.schoolName) {
                                    focusManager.clearFocus()
                                    schoolName = it.schoolName
                                }
                            }
                        }
                    }
                }
            }
        } else {
            Spacer(modifier = Modifier.height(28.dp))
            SchoolTextField(
                title = "이름",
                value = name,
                onValueChange = { name = it },
                hint = "이름을 입력해주세요."
            )
            if (nameErrorText.isNotEmpty()) {
                BodySmallText(
                    modifier = Modifier.padding(top = 8.dp, bottom = 6.dp, start = 16.dp),
                    text = nameErrorText,
                    color = SchoolTheme.colors.error
                )
            } else {
                Spacer(modifier = Modifier.height(28.dp))
            }
            StudentTextField(
                grade = grade,
                onGradeChange = { grade = it },
                `class` = `class`,
                onClassChange = { `class` = it },
                number = number,
                onNumberChange = { number = it },
            )
        }

        Spacer(modifier = Modifier.height(40.dp))
        SchoolButton(
            text = "넘어가기",
            activate = (state.schoolName.isEmpty() && schoolName.isNotEmpty()) || (schoolName.isNotEmpty() && grade.isNotEmpty() && `class`.isNotEmpty() && number.isNotEmpty() && name.isNotEmpty())
        ) {
            if (!isSelectSchool) {
                signupViewModel.saveSchool(schoolName = schoolName)
                popProfile?.invoke(schoolName)
            } else {
                if (name.nameLengthCheck().not()) {
                    nameErrorText = "이름의 글자 수는 2 ~ 10자 사이여야 합니다."
                } else {
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
}

@Composable
fun SchoolText(schoolName: String, onSelect: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(horizontal = 14.dp)
            .fillMaxWidth()
            .padding(top = 15.dp, bottom = 15.dp)
            .schoolClickable { onSelect() }
    ) {
        BodyMediumText(text = schoolName, color = SchoolTheme.colors.black)
        Spacer(modifier = Modifier.height(15.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(SchoolTheme.colors.lightGray)
        )
    }
}