package com.school.core.ui.component.datepicker

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.school.core.design_system.SchoolTheme
import com.school.core.ui.component.textview.BodyMediumText
import com.school.core.ui.component.textview.TitleMediumText
import com.school.core.ui.util.data.dayOfWeekList
import com.school.core.ui.util.data.getSpaceCount
import com.school.core.ui.util.data.getStartOrLastDate
import com.school.core.ui.util.data.setDate
import com.school.core.ui.util.modifier.schoolClickable
import java.time.LocalDate

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarDatePicker(
    modifier: Modifier = Modifier,
    currentDate: LocalDate,
    onSelect: (LocalDate) -> Unit,
) {
    val spaceCount = currentDate.getSpaceCount()
    var pagerIdx by remember { mutableIntStateOf(Int.MAX_VALUE / 2) }
    val pagerState = rememberPagerState(initialPage = pagerIdx) {
        Int.MAX_VALUE
    }

    LaunchedEffect(pagerState.currentPage) {
        val currentPage = pagerState.currentPage
        if (currentPage > pagerIdx) {
            onSelect(currentDate.plusMonths(1))
        } else if (currentPage < pagerIdx) {
            onSelect(currentDate.minusMonths(1))
        }
        pagerIdx = currentPage
    }

    HorizontalPager(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        state = pagerState,
    ) {
        if (it in pagerState.currentPage - 1..pagerState.currentPage + 1) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(7),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalArrangement = Arrangement.spacedBy(17.dp)
            ) {
                items(dayOfWeekList) {
                    BodyMediumText(text = it, color = SchoolTheme.colors.black)
                }
                items(spaceCount, itemContent = {})
                items((1..currentDate.getStartOrLastDate(isStart = false).dayOfMonth).toList()) {
                    val isSelected = currentDate.dayOfMonth == it
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        TitleMediumText(
                            modifier = Modifier
                                .schoolClickable {
                                    onSelect(currentDate.setDate(date = it))
                                },
                            text = it.toString(),
                            color = SchoolTheme.colors.black
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .background(
                                    color = if (isSelected) SchoolTheme.colors.main else Color.Transparent,
                                    shape = CircleShape
                                )
                        )
                    }
                }
            }
        }
    }
}