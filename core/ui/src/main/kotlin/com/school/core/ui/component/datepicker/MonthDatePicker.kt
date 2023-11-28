package com.school.core.ui.component.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.util.data.dayOfWeekList
import com.school.core.ui.util.data.getSpaceCount
import com.school.core.ui.util.data.getStartOrLastDate
import com.school.core.ui.util.data.setDate
import com.school.core.ui.util.modifier.schoolClickable
import java.time.LocalDate

@Composable
fun MonthDatePicker(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    onSelect: (LocalDate) -> Unit,
) {
    val spaceCount = currentDate.getSpaceCount()
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SchoolIcon(
            modifier = Modifier.schoolClickable { onSelect(currentDate.minusMonths(1)) },
            icon = SchoolIconList.PrevMonth
        )
        LazyVerticalGrid(
            modifier = modifier
                .weight(1F),
            columns = GridCells.Fixed(7),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(dayOfWeekList) {
                BodyMediumText(text = it, color = SchoolTheme.colors.black)
            }
            items(spaceCount, itemContent = {})
            items((1..currentDate.getStartOrLastDate(isStart = false).dayOfMonth).toList()) {
                val isSelected = currentDate.dayOfMonth == it
                BodyMediumText(
                    modifier = Modifier
                        .wrapContentSize()
                        .background(
                            color = if (isSelected) SchoolTheme.colors.main else Color.Transparent,
                            shape = CircleShape
                        )
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .schoolClickable {
                            onSelect(currentDate.setDate(date = it))
                        },
                    text = it.toString(),
                    color = if (isSelected) SchoolTheme.colors.white else SchoolTheme.colors.gray
                )
            }
        }
        SchoolIcon(
            modifier = Modifier.schoolClickable { onSelect(currentDate.plusMonths(1)) },
            icon = SchoolIconList.NextMonth
        )
    }
}

@Preview
@Composable
fun PreviewMonthDatePicker() {
    SchoolTheme {
        MonthDatePicker(currentDate = LocalDate.now()) {

        }
    }
}