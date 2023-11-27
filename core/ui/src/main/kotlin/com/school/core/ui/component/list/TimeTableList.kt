package com.school.core.ui.component.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.textview.BodyLargeText

@Composable
fun TimetableList(
    modifier: Modifier = Modifier,
    timeTable: List<String>,
) {
    RemoveOverScrollLazyColumn(
        modifier = modifier
            .shadow(
                elevation = 16.dp,
                spotColor = Color(0x1A3D3D3D),
                ambientColor = Color(0x1A3D3D3D),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .background(color = SchoolTheme.colors.white, shape = RoundedCornerShape(20.dp))
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(timeTable) {
            BodyLargeText(
                text = it,
                fontWeight = FontWeight.Medium,
                color = SchoolTheme.colors.black
            )
        }
    }
}

@Preview
@Composable
fun PreviewScheduleList() {
    SchoolTheme {
        TimetableList(
            timeTable = listOf(
                "1. 국어",
                "2. 사회",
                "3. 산업소프트웨어",
                "4. 수학",
                "5. 동아리",
                "6. 동아리",
                "7. 동아리"
            )
        )
    }
}