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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.datepicker.CalendarDatePicker
import com.school.core.ui.component.textview.BodySmallText
import com.school.core.ui.component.textview.FugazOneText
import java.time.LocalDate

@Composable
fun ScheduleScreen() {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }
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
            CalendarDatePicker(currentDate = currentDate) {
                currentDate = it
            }
            Spacer(modifier = Modifier.height(30.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .verticalScroll(state = rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth()
                        .background(
                            color = SchoolTheme.colors.lightGray2,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(vertical = 34.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    SchoolIcon(icon = SchoolIconList.AddSchedule)
                    BodySmallText(text = "일정 추가하기", color = SchoolTheme.colors.gray)
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
    }
}