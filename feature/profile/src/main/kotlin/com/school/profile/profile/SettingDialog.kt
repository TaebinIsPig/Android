package com.school.profile.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.util.modifier.schoolClickable

@Composable
fun SettingDialog(
    editProfile: () -> Unit,
    signOut: () -> Unit,
    withdraw: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = SchoolTheme.colors.black,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            )
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .padding(horizontal = 140.dp)
                .height(2.dp)
                .fillMaxWidth()
                .background(color = SchoolTheme.colors.gray, shape = RoundedCornerShape(5.dp))
        )
        Spacer(modifier = Modifier.height(40.dp))
        SettingItem(icon = SchoolIconList.ProfileSetting, name = "프로필 수정", onClick = editProfile)
        Spacer(modifier = Modifier.height(24.dp))
        SettingItem(icon = SchoolIconList.SignOut, name = "로그아웃", onClick = signOut)
        Spacer(modifier = Modifier.height(24.dp))
        SettingItem(icon = SchoolIconList.Withdrawal, name = "회원탈퇴", onClick = withdraw)
        Spacer(modifier = Modifier.height(160.dp))
    }
}

@Composable
fun SettingItem(icon: SchoolIconList, name: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier.schoolClickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SchoolIcon(icon = icon)
        Spacer(modifier = Modifier.width(8.dp))
        BodyMediumText(text = name)
    }
}