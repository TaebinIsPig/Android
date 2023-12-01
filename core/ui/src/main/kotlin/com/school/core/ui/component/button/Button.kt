package com.school.core.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.textview.TitleSmallText
import com.school.core.ui.util.modifier.schoolClickable

@Composable
fun SchoolButton(
    modifier: Modifier = Modifier,
    activate: Boolean = true,
    text: String,
    onClick: () -> Unit,
) {
    TitleSmallText(
        modifier = modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(
                color = if (activate) SchoolTheme.colors.pink3 else SchoolTheme.colors.lightGray,
                shape = RoundedCornerShape(10.dp)
            )
            .padding(vertical = 20.dp)
            .schoolClickable(onClick = {
                if (activate) onClick()
            }),
        text = text,
        fontWeight = FontWeight.SemiBold
    )
}

@Preview
@Composable
fun PreviewSchoolButton() {
    SchoolTheme {
        SchoolButton(text = "로그인") {

        }
    }
}