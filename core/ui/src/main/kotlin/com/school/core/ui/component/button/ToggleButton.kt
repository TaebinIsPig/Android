package com.school.core.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.textview.BodyLargeText
import com.school.core.ui.util.modifier.schoolClickable

enum class ToggleMenu {
    Cafeteria,
    TimeTable
}

@Composable
fun ToggleButton(
    modifier: Modifier = Modifier,
    currentToggle: ToggleMenu,
    clickToggle: () -> Unit,
) {
    Row(
        modifier = modifier
            .background(
                color = SchoolTheme.colors.pink2,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(vertical = 2.dp, horizontal = 1.dp)
    ) {
        BodyLargeText(
            modifier = Modifier
                .background(
                    color = if (currentToggle == ToggleMenu.Cafeteria) SchoolTheme.colors.main else Color.Transparent,
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(vertical = 9.dp, horizontal = 30.dp)
                .schoolClickable(onClick = clickToggle),
            text = "급식",
            fontWeight = FontWeight.SemiBold
        )
        BodyLargeText(
            modifier = Modifier
                .background(
                    color = if (currentToggle == ToggleMenu.TimeTable) SchoolTheme.colors.main else Color.Transparent,
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(vertical = 9.dp, horizontal = 23.dp)
                .schoolClickable(onClick = clickToggle),
            text = "시간표",
            fontWeight = FontWeight.SemiBold
        )
    }
}

@Preview
@Composable
fun previewToggleButton() {
    SchoolTheme {
        ToggleButton(currentToggle = ToggleMenu.Cafeteria) {

        }
    }
}