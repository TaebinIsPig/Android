package com.school.profile.profile

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.textfield.SchoolTextField
import com.school.core.ui.component.textfield.StudentTextField
import com.school.core.ui.component.textview.BodyLargeText
import com.school.core.ui.component.textview.FugazOneText
import com.school.core.ui.util.modifier.schoolClickable
import com.school.profile.edit_profile.EditProfileDialog
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ProfileScreen(
    popBackStack: () -> Unit,
    navigateChangePhoneNumber: () -> Unit,
    navigateChangeSchool: () -> Unit,
    profileViewModel: ProfileViewModel = hiltViewModel(),
) {
    val container = profileViewModel.container
    val state = container.stateFlow.collectAsState().value
    val sheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)
    val coroutineScope = rememberCoroutineScope()
    val myProfile = state.myProfileEntity
    var editProfileVisible by remember { mutableStateOf(false) }

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
    }

    LaunchedEffect(Unit) {
        profileViewModel.myProfile()
    }

    ModalBottomSheetLayout(
        sheetContent = {
            SettingDialog(
                editProfile = {
                    coroutineScope.launch {
                        sheetState.hide()
                        editProfileVisible = true
                    }
                },
                signOut = {},
                withdraw = {})
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent
    ) {
        EditProfileDialog(
            visible = editProfileVisible,
            myProfileEntity = state.myProfileEntity,
            hideVisible = { editProfileVisible = false },
            changePhoneNumber = navigateChangePhoneNumber,
            changeSchool = navigateChangeSchool,
            changeProfile = {},
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(SchoolTheme.colors.main)
        ) {
            Column(
                modifier = Modifier
                    .weight(0.35F),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    SchoolIcon(
                        modifier = Modifier.schoolClickable(onClick = popBackStack),
                        icon = SchoolIconList.Back
                    )
                    FugazOneText(text = "My Profile", textSize = 18)
                    SchoolIcon(
                        modifier = Modifier.schoolClickable {
                            coroutineScope.launch { sheetState.show() }
                        },
                        icon = SchoolIconList.Setting
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
                SchoolIcon(icon = SchoolIconList.DefaultProfile)
                Spacer(modifier = Modifier.height(8.dp))
                BodyLargeText(
                    text = myProfile.name,
                    fontWeight = FontWeight.SemiBold,
                )
            }
            Box(
                modifier = Modifier
                    .weight(0.65F)
                    .fillMaxWidth()
                    .background(SchoolTheme.colors.lightGray3),
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .offset(y = (-40).dp)
                        .background(
                            color = SchoolTheme.colors.white,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 14.dp)
                ) {
                    Spacer(modifier = Modifier.height(30.dp))
                    SchoolTextField(
                        title = "학교",
                        value = myProfile.school,
                        onValueChange = { },
                        readOnly = true
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    StudentTextField(
                        readOnly = true,
                        grade = myProfile.studentInfo.grade.toString(),
                        onGradeChange = {},
                        `class` = myProfile.studentInfo.`class`.toString(),
                        onClassChange = {},
                        number = myProfile.studentInfo.number.toString(),
                        onNumberChange = {}
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    SchoolTextField(
                        title = "전화번호",
                        value = myProfile.phoneNumber,
                        onValueChange = { },
                        readOnly = true
                    )
                    Spacer(modifier = Modifier.weight(1F))
                }
            }
        }
    }
}