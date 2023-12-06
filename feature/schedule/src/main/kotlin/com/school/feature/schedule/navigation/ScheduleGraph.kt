package com.school.feature.schedule.navigation

import androidx.navigation.NavGraphBuilder
import com.school.core.ui.util.ui.extraComposable
import com.school.feature.schedule.schedule.ScheduleScreen

fun NavGraphBuilder.scheduleGraph(isBackHome: Boolean, changePreviousRoute: (String) -> Unit) {
    extraComposable(
        route = ScheduleNavigationItem.Schedule.route,
        isBackHome = isBackHome
    ) {
        ScheduleScreen()
        changePreviousRoute(ScheduleNavigationItem.Schedule.route)
    }
}