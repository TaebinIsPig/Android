package com.school.core.ui.component.button

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.list.RemoveOverScrollLazyRow
import com.school.core.ui.component.textview.BodyLargeText
import com.school.core.ui.util.modifier.schoolClickable

enum class ToggleMenu(val type: String) {
    Cafeteria(type = "급식"),
    TimeTable(type = "시간표")
}

@Composable
fun ToggleButton(
    modifier: Modifier = Modifier,
    currentToggle: ToggleMenu,
    onClickToggle: (ToggleMenu) -> Unit,
) {
    RemoveOverScrollLazyRow(
        modifier = modifier
            .background(
                color = SchoolTheme.colors.pink2,
                shape = RoundedCornerShape(50.dp)
            )
            .padding(vertical = 2.dp, horizontal = 1.dp)
    ) {
        items(ToggleMenu.values().toList()) {
            BodyLargeText(
                modifier = Modifier
                    .background(
                        color = if (currentToggle == it) SchoolTheme.colors.main else Color.Transparent,
                        shape = RoundedCornerShape(100.dp)
                    )
                    .padding(vertical = 9.dp, horizontal = 30.dp)
                    .schoolClickable(onClick = { onClickToggle(it) }),
                text = it.type,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun previewToggleButton() {
    var currentMenu by remember { mutableStateOf(ToggleMenu.Cafeteria) }
    SchoolTheme {
        ToggleButton(currentToggle = currentMenu) {
            currentMenu = it
        }
    }
}