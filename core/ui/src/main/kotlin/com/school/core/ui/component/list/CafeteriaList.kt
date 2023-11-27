package com.school.core.ui.component.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.textview.BodyLargeText
import com.school.core.ui.component.textview.TitleSmallText
import com.school.core.ui.util.modifier.schoolClickable

enum class MealType(val type: String) {
    Breakfast(type = "조식"),
    Launch(type = "중식"),
    Dinner(type = "석식")
}

@Composable
fun CafeteriaList(
    modifier: Modifier = Modifier,
    cafeteria: List<String>,
    calorie: Float,
    onTabHeaderClick: ((MealType) -> Unit)? = null,
) {
    Column(
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
            .padding(top = 8.dp, bottom = 28.dp, start = 20.dp, end = 20.dp)
    ) {
        onTabHeaderClick?.let {
            var currentMealType by remember { mutableStateOf(MealType.Breakfast) }
            MealTabHeader(currentMealType = currentMealType, onClick = {
                if (currentMealType != it) {
                    currentMealType = it
                    onTabHeaderClick(it)
                }
            })
        }
        Spacer(modifier = Modifier.height(24.dp))
        RemoveOverScrollLazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            items(cafeteria) {
                BodyLargeText(
                    text = it,
                    fontWeight = FontWeight.Medium,
                    color = SchoolTheme.colors.black
                )
            }
        }
        Spacer(modifier = Modifier.height(45.dp))
        BodyLargeText(
            modifier = Modifier.align(End),
            text = "${calorie}kcal",
            fontWeight = FontWeight.Medium,
            color = SchoolTheme.colors.black
        )
    }
}

@Composable
fun MealTabHeader(
    modifier: Modifier = Modifier,
    currentMealType: MealType,
    onClick: (MealType) -> Unit,
) {
    RemoveOverScrollLazyRow(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(MealType.values().toList()) {
            Column(
                modifier = Modifier.schoolClickable { onClick(it) },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleSmallText(
                    text = it.type,
                    fontWeight = FontWeight.Medium,
                    color = if (currentMealType == it) SchoolTheme.colors.black else SchoolTheme.colors.lightGray2
                )
                Spacer(modifier = Modifier.height(6.dp))
                if (currentMealType == it) {
                    Box(
                        modifier = Modifier
                            .size(4.dp)
                            .background(
                                color = SchoolTheme.colors.main,
                                shape = CircleShape
                            )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewCafeteriaList() {
    SchoolTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            CafeteriaList(
                cafeteria = listOf(
                    "친환경백미밥자율",
                    "닭가슴살샐러드",
                    "미트볼케찹볶음",
                    "배추김치",
                    "바나나",
                    "시리얼&우유"
                ), calorie = 721.2F
            )
            CafeteriaList(
                cafeteria = listOf(
                    "친환경백미밥자율",
                    "닭가슴살샐러드",
                    "미트볼케찹볶음",
                    "배추김치",
                    "바나나",
                    "시리얼&우유"
                ), calorie = 721.2F
            ) {

            }
        }
    }
}