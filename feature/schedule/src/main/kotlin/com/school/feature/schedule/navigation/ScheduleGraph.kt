package com.school.feature.schedule.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.school.feature.schedule.schedule.ScheduleScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.scheduleGraph() {
    composable(ScheduleNavigationItem.Schedule.route) {
        ScheduleScreen()
    }
}