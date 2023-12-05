package com.school.feature.schedule.schedule

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.textview.FugazOneText

@Composable
fun ScheduleScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SchoolTheme.colors.main)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3F)
                .padding(top = 9.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                FugazOneText(text = "Today's", textSize = 28)
                FugazOneText(text = "Calendar", textSize = 28)
            }
            SchoolIcon(icon = SchoolIconList.Schedule)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .background(
                    color = SchoolTheme.colors.white,
                    shape = RoundedCornerShape(topEnd = 50.dp)
                )
        ) {

        }
    }
}