package com.school.feature.main.main

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
import com.school.core.ui.component.button.ToggleButton
import com.school.core.ui.component.button.ToggleMenu
import com.school.core.ui.component.list.CafeteriaList
import com.school.core.ui.component.list.TimetableList
import com.school.core.ui.component.textview.BodyLargeText
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.component.textview.TitleMediumText
import com.school.core.ui.util.modifier.schoolClickable

@Composable
fun MainScreen(
    navigateProfile: () -> Unit,
) {
    var currentToggle by remember { mutableStateOf(ToggleMenu.Cafeteria) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(SchoolTheme.colors.main)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyLargeText(text = "로고")
            SchoolIcon(
                modifier = Modifier.schoolClickable(onClick = navigateProfile),
                icon = SchoolIconList.Profile
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        BodyLargeText(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "광주소프트웨어마이스터고등학교",
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BodyMediumText(
                text = "2학년 3반",
                fontWeight = FontWeight.Medium
            )
            BodyMediumText(
                text = "이동욱",
                fontWeight = FontWeight.Medium
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)
                .background(
                    color = SchoolTheme.colors.white,
                    shape = RoundedCornerShape(topStart = 50.dp)
                )
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Row(
                modifier = Modifier.padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                TitleMediumText(
                    text = "2023",
                    fontWeight = FontWeight.SemiBold,
                    color = SchoolTheme.colors.black
                )
                BodyMediumText(
                    text = "10월 24일 (화)",
                    fontWeight = FontWeight.Medium,
                    color = SchoolTheme.colors.black
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            ToggleButton(
                modifier = Modifier.padding(horizontal = 16.dp),
                currentToggle = currentToggle,
                onClickToggle = { currentToggle = it })
            Spacer(modifier = Modifier.height(20.dp))
            when (currentToggle) {
                ToggleMenu.Cafeteria -> {
                    CafeteriaList(
                        modifier = Modifier
                            .weight(1F)
                            .padding(bottom = 23.dp),
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

                ToggleMenu.TimeTable -> {
                    TimetableList(
                        modifier = Modifier
                            .weight(1F)
                            .padding(bottom = 23.dp),
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
        }
    }
}