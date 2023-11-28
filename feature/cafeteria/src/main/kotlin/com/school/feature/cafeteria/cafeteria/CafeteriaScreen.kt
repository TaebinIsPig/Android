package com.school.feature.cafeteria.cafeteria

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.design_system.attribute.SchoolIcon
import com.school.core.design_system.attribute.SchoolIconList
import com.school.core.ui.component.button.OpenDatePickerButton
import com.school.core.ui.component.datepicker.MonthDatePicker
import com.school.core.ui.component.list.CafeteriaList
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.component.textview.FugazOneText
import com.school.core.ui.component.textview.TitleMediumText
import com.school.core.ui.util.data.toDisplayDate
import java.time.LocalDate

@Composable
fun CafeteriaScreen() {
    var currentDate by remember { mutableStateOf(LocalDate.now()) }
    var isOpenDatePicker by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SchoolTheme.colors.main)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                FugazOneText(text = "Today's", textSize = 28)
                FugazOneText(text = "Menu!", textSize = 28)
            }
            SchoolIcon(icon = SchoolIconList.Cafeteria)
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
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
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
                OpenDatePickerButton(isOpen = isOpenDatePicker) {
                    isOpenDatePicker = !isOpenDatePicker
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            AnimatedVisibility(visible = isOpenDatePicker) {
                MonthDatePicker(currentDate = currentDate) {
                    currentDate = it
                }
            }
            CafeteriaList(
                modifier = Modifier
                    .weight(1F)
                    .padding(bottom = 23.dp, top = if (isOpenDatePicker) 24.dp else 0.dp),
                cafeteria = listOf(
                    "친환경백미밥자율",
                    "닭가슴살샐러드",
                    "미트볼케찹볶음",
                    "배추김치",
                    "바나나",
                    "시리얼&우유"
                ),
                calorie = 721.2F
            )
        }
    }
}