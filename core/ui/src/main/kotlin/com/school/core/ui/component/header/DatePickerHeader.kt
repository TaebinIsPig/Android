package com.school.core.ui.component.header

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.button.OpenDatePickerButton
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.component.textview.BodySmallText
import com.school.core.ui.component.textview.TitleMediumText
import com.school.core.ui.util.data.toDisplayDate
import com.school.core.ui.util.modifier.schoolClickable
import java.time.LocalDate

@Composable
fun DatePickerHeader(
    currentDate: LocalDate,
    isOpenDatePicker: Boolean? = null,
    clickOpenDatePicker: (Boolean) -> Unit = {},
    clickCurrentDate: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        TitleMediumText(
            text = currentDate.year.toString(),
            fontWeight = FontWeight.SemiBold,
            color = SchoolTheme.colors.black
        )
        BodyMediumText(
            text = currentDate.toDisplayDate(),
            fontWeight = FontWeight.Medium,
            color = SchoolTheme.colors.black
        )
        isOpenDatePicker?.let {
            OpenDatePickerButton(isOpen = isOpenDatePicker) {
                clickOpenDatePicker(!isOpenDatePicker)
            }
        }
        Box(modifier = Modifier.weight(1F))
        clickCurrentDate?.let {
            Box {
                SchoolIcon(
                    modifier = Modifier.schoolClickable(onClick = clickCurrentDate),
                    icon = SchoolIconList.CurrentDate
                )
                BodySmallText(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .offset(y = 1.dp),
                    text = LocalDate.now().dayOfMonth.toString(),
                    color = SchoolTheme.colors.pink3
                )
            }
        }
    }
}