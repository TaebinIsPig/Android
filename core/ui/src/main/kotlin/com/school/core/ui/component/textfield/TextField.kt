package com.school.core.ui.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.util.modifier.schoolClickable

@Composable
fun SchoolTextField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    hint: String,
    betweenSpace: Int = 11,
    onValueChange: (String) -> Unit,
    maxLines: Int = 1,
    readOnly: Boolean = false,
    imeAction: ImeAction = ImeAction.Done,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordVisible: Boolean? = null,
    tailingIcon: (@Composable () -> Unit)? = null,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(),
    ) {
        BodyMediumText(
            text = title,
            fontWeight = FontWeight.SemiBold,
            color = SchoolTheme.colors.black,
        )
        Spacer(modifier = Modifier.height(betweenSpace.dp))
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = SchoolTheme.typography.bodyMedium.copy(color = SchoolTheme.colors.black),
            maxLines = maxLines,
            singleLine = maxLines == 1,
            readOnly = readOnly,
            keyboardOptions = KeyboardOptions(imeAction = imeAction, keyboardType = keyboardType),
            visualTransformation = if (isPasswordVisible == null || !isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier.weight(1F)
                ) {
                    it()
                    if (value.isEmpty()) {
                        BodyMediumText(text = hint, color = SchoolTheme.colors.gray)
                    }

                }
                tailingIcon?.let { it() }
            }
        }
        Spacer(modifier = Modifier.height(7.dp))
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(SchoolTheme.colors.lightGray)
        )
    }
}

@Composable
fun PasswordVisibleIcon(
    modifier: Modifier = Modifier,
    isPasswordVisible: Boolean,
    changePasswordVisible: () -> Unit,
) {
    SchoolIcon(
        modifier = modifier
            .schoolClickable(onClick = changePasswordVisible)
            .size(22.dp),
        icon = if (isPasswordVisible) SchoolIconList.PasswordVisible else SchoolIconList.PasswordHide
    )
}

@Composable
fun SearchButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    BodyMediumText(
        modifier = modifier
            .background(
                color = SchoolTheme.colors.pink3,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(vertical = 6.dp, horizontal = 12.dp)
            .schoolClickable(onClick = onClick),
        text = "검색"
    )
}

@Preview
@Composable
fun PreviewSchoolTextField() {
    var text by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(true) }
    SchoolTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            SchoolTextField(
                title = "아이디",
                hint = "아이디를 입력해주세요.",
                value = text,
                onValueChange = { text = it },
            )
            SchoolTextField(
                title = "비밀번호",
                hint = "비밀번호를 입력해주세요.",
                value = text,
                onValueChange = { text = it },
                isPasswordVisible = isPasswordVisible,
                tailingIcon = {
                    PasswordVisibleIcon(isPasswordVisible = isPasswordVisible) {
                        isPasswordVisible = !isPasswordVisible
                    }
                }
            )
            SchoolTextField(
                title = "학교",
                hint = "학교 이름을 입력해주세요.",
                value = text,
                betweenSpace = 2,
                onValueChange = { text = it },
                tailingIcon = {
                    SearchButton {

                    }
                }
            )
        }
    }
}