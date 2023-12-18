package com.school.profile.edit_profile

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.domain.entity.account.MyProfileEntity
import com.school.core.ui.component.button.SchoolButton
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textfield.StudentTextField
import com.school.core.ui.component.textfield.TailingButton
import com.school.core.ui.util.modifier.schoolClickable

@Composable
fun EditProfileDialog(
    visible: Boolean,
    myProfileEntity: MyProfileEntity,
    hideVisible: () -> Unit,
    changePhoneNumber: () -> Unit,
    changeSchool: () -> Unit,
    changeProfile: () -> Unit,
) {
    var name by remember { mutableStateOf(myProfileEntity.name) }
    var grade by remember { mutableStateOf(myProfileEntity.studentInfo.grade.toString()) }
    var `class` by remember { mutableStateOf(myProfileEntity.studentInfo.`class`.toString()) }
    var number by remember { mutableStateOf(myProfileEntity.studentInfo.number.toString()) }

    LaunchedEffect(myProfileEntity.name) {
        name = myProfileEntity.name
        grade = myProfileEntity.studentInfo.grade.toString()
        `class` = myProfileEntity.studentInfo.`class`.toString()
        number = myProfileEntity.studentInfo.number.toString()
    }

    AnimatedVisibility(visible = visible) {
        Dialog(
            onDismissRequest = hideVisible,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Column(
                modifier = Modifier
                    .padding(vertical = 70.dp, horizontal = 16.dp)
                    .fillMaxSize()
                    .background(color = SchoolTheme.colors.white, shape = RoundedCornerShape(10.dp))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                ) {
                    SchoolIcon(
                        modifier = Modifier
                            .align(TopEnd)
                            .padding(end = 8.dp)
                            .schoolClickable(onClick = hideVisible),
                        icon = SchoolIconList.CloseDialog
                    )
                    SchoolIcon(
                        modifier = Modifier
                            .align(Center)
                            .padding(top = 22.dp),
                        icon = SchoolIconList.DefaultProfile
                    )
                }
                Column(
                    modifier = Modifier.padding(horizontal = 14.dp)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    SchoolTextField(
                        title = "이름",
                        value = name,
                        onValueChange = { name = it },
                        hint = "이름을 입력해주세요."
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SchoolTextField(
                        title = "전화번호",
                        value = myProfileEntity.phoneNumber,
                        readOnly = true,
                        onValueChange = { },
                        hint = "전화번호를 입력해주세요.",
                        tailingIcon = {
                            TailingButton(title = "변경", onClick = changePhoneNumber)
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    SchoolTextField(
                        title = "학교",
                        value = myProfileEntity.school,
                        readOnly = true,
                        onValueChange = { },
                        hint = "학교 이름을 입력해주세요.",
                        tailingIcon = {
                            TailingButton(title = "변경", onClick = changeSchool)
                        }
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    StudentTextField(
                        grade = grade,
                        onGradeChange = { grade = it },
                        `class` = `class`,
                        onClassChange = { `class` = it },
                        number = number,
                        onNumberChange = { number = it }
                    )
                }
                Spacer(modifier = Modifier.weight(1F))
                SchoolButton(text = "저장하기", horizontalPadding = 8, onClick = changeProfile)
                Spacer(modifier = Modifier.height(34.dp))
            }
        }
    }
}