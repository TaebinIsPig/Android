package com.school.core.ui.component.datepicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.list.RemoveOverScrollLazyRow
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.util.data.dayOfWeekList
import com.school.core.ui.util.data.getDayOfWeek
import com.school.core.ui.util.modifier.schoolClickable
import java.time.LocalDate
import java.util.Locale

@Composable
fun WeekDatePicker(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    onSelect: (LocalDate) -> Unit,
) {
    val currentDateIdx = dayOfWeekList.indexOf(currentDate.getDayOfWeek(Locale.US))
    RemoveOverScrollLazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        itemsIndexed(dayOfWeekList) { index, item ->
            val isSelected = currentDateIdx == index
            val date = currentDate.plusDays(index - currentDateIdx.toLong())
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        color = if (isSelected) SchoolTheme.colors.main else Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 6.dp)
                    .schoolClickable {
                        onSelect(date)
                    },
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                BodyMediumText(
                    text = item,
                    color = if (isSelected) SchoolTheme.colors.white else SchoolTheme.colors.gray
                )
                BodyMediumText(
                    text = date.dayOfMonth.toString(),
                    color = if (isSelected) SchoolTheme.colors.white else SchoolTheme.colors.gray
                )
            }
        }
    }
}