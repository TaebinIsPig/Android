package com.school.core.ui.component.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.textview.BodyLargeText
import com.school.core.ui.component.textview.BodyMediumText

@Composable
fun StudentTextField(
    readOnly: Boolean = false,
    grade: String,
    onGradeChange: (String) -> Unit,
    `class`: String,
    onClassChange: (String) -> Unit,
    number: String,
    onNumberChange: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        BodyMediumText(
            text = "학년/반/번호",
            fontWeight = FontWeight.SemiBold,
            color = SchoolTheme.colors.black
        )
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            StudentInfoTextField(
                modifier = Modifier.weight(1F),
                maxLength = 2,
                value = grade,
                hint = "학년",
                tailingText = "학년",
                onValueChange = onGradeChange,
                readOnly = readOnly
            )
            StudentInfoTextField(
                modifier = Modifier.weight(1F),
                value = `class`,
                hint = "반",
                tailingText = "반",
                onValueChange = onClassChange,
                readOnly = readOnly
            )
            StudentInfoTextField(
                modifier = Modifier.weight(1F),
                value = number,
                hint = "번호",
                tailingText = "번",
                onValueChange = onNumberChange,
                readOnly = readOnly
            )
        }
    }
}

@Composable
private fun StudentInfoTextField(
    modifier: Modifier = Modifier,
    value: String,
    maxLength: Int = 3,
    hint: String,
    tailingText: String,
    onValueChange: (String) -> Unit,
    readOnly: Boolean,
) {
    BasicTextField(
        modifier = modifier
            .border(
                width = 1.dp,
                color = if (readOnly) SchoolTheme.colors.lightGray3 else SchoolTheme.colors.lightGray,
                shape = RoundedCornerShape(5.dp)
            )
            .padding(vertical = 7.dp, horizontal = 12.dp),
        value = value,
        readOnly = readOnly,
        onValueChange = { if (it.length < maxLength) onValueChange(it) },
        maxLines = 1,
        singleLine = true,
        textStyle = SchoolTheme.typography.bodyLarge.copy(color = SchoolTheme.colors.black),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    ) {
        Box {
            it()
            if (value.isNotEmpty()) {
                BodyLargeText(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    text = tailingText,
                    color = SchoolTheme.colors.black
                )
            }
            if (value.isEmpty()) {
                BodyLargeText(text = hint, color = SchoolTheme.colors.gray)
            }
        }
    }
}